package com.babasnack.demo.orderbuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.cart.dto.CartWithPhoto;
import com.babasnack.demo.entity.Delivery;
import com.babasnack.demo.orderbuy.dao.OrderBuyDao;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto.OrderBuyProduct;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto.ReadOrderDetailByOB;

@Service
public class OrderBuyService {
	@Autowired
	private OrderBuyDao orderBuyDao;

	// 장바구니 목록과 가격 합계를 CartDto.ReadCart에 담아 리턴(주문용)
	@Transactional(readOnly = true)
	public OrderBuyDto.ReadCartOB readCart(String username) {
		List<CartWithPhoto> cart = orderBuyDao.findByUsernameFromCartOB(username);
		Long productCnt = orderBuyDao.allProductCntByUsernameFromCartOB(username);
		Long allPrice = orderBuyDao.sumProductByUsernameFromCartOB(username);

		return new OrderBuyDto.ReadCartOB(cart, productCnt, allPrice);
	}

	// 주문 정보 저장
	public Boolean add(OrderBuyDto.OrderBuyProduct orderBuyProduct, String username) {
		return orderBuyDao.addOrderBuy(orderBuyProduct, username) == 1;
	}

	// 모든 상품의 10% 적립금 저장(update문)
	public void productReserve() {
		orderBuyDao.updateProductReserve();
	}

	// 장바구니에서 전체주문(적립금 누적, 적립금 사용시 적립금 누적감소 + 적립금만큼 총주문액 감소) - 작성+수정중
	public void orderBuyCartInsert(Long reservePlus, Long allReserve, Long ono, String username) {

		OrderBuyDto.OrderBuyProduct orderBuyProduct = orderBuyDao.findOBByUsernameAndOno(username, ono);
		allReserve = orderBuyProduct.getAllReserve();

		OrderBuyDto.ReserveByOrderBuy reserveByOrderBuy = orderBuyDao.findReserveByUsernameAndOno(username, ono);
		Long totoalReserve = reserveByOrderBuy.getReservePlus();

		if (totoalReserve != 0) {
			if (reservePlus < totoalReserve) {
				orderBuyDao.updatePlusReserve(allReserve, ono, username);
			} else {
				orderBuyDao.updateMinusOBAllPrice(reservePlus, ono, username);
				orderBuyDao.updateMinusReserve(reservePlus, ono, username);
			}
		} else {
			orderBuyDao.updatePlusReserve(allReserve, ono, username);
		}
	}

	// 로그인한 회원의 주문번호+아이디 가져오기
	public OrderBuyProduct findByUsernameAndOno(String username, Long ono) {
		return orderBuyDao.findByUsernameAndOno(username, ono);
	}

	// 회원 정보 읽기(delivery 테이블)
	public OrderBuyDto.DeliveryByOrderBuy readDelivery(String username) {
		Delivery delivery = orderBuyDao.findUsernameFromDelivery(username);
		return new OrderBuyDto.DeliveryByOrderBuy(delivery.getUsername(), delivery.getName(), delivery.getPnoTell(),
				delivery.getBaseDelivery());

	}

	// 주문후 상품 수량 감소
	public Boolean decreaseProduct(OrderBuyDto.ProductOrderBuyDto productOrderBuyDto, Long pno) {
		productOrderBuyDto = orderBuyDao.findProductById(pno);
		if (productOrderBuyDto.getProductStock() < 0)
			return false;
		return orderBuyDao.decreaseProductStock(productOrderBuyDto) == 1;
	}

	// 주문 후 상품 수량 감소(odno 출력용)
	public List<ReadOrderDetailByOB> findOrderDetailByOdno(Long odno) {
		return orderBuyDao.findOrderDetailByOdno(odno);
	}

	// 주문후 장바구니 초기화(전체삭제)
	public void deleteAllCartByUsername(String username) {
		orderBuyDao.deleteCartByUsername(username);
	}

	// 회원 탈퇴 후 적립금 삭제
	public void deleteAllReserveByUsername(String username) {
		orderBuyDao.deleteReserveByUsername(username);
	}

	// 주문상품 발송여부(관리자)
	public Boolean deliveryUpdate(String deliveryState, String username, Long ono) {
		OrderBuyDto.OrderBuyProduct orderBuy = orderBuyDao.findByUsernameAndOno(username, ono);
		if (orderBuy != null) {
			if (deliveryState != "배송준비")
				return false;
		}
		return orderBuyDao.updateDeliveryState(deliveryState, username, ono) == 1;
	}
}
