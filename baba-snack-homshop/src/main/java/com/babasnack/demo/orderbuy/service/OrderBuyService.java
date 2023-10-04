package com.babasnack.demo.orderbuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.orderbuy.dao.OrderBuyDao;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto;

@Service
public class OrderBuyService {
	@Autowired
	private OrderBuyDao orderBuyDao;

	// 주문 정보 저장
	public Boolean add(OrderBuyDto.OrderBuyProduct orderBuyProduct, String username) {
		return orderBuyDao.addOrderBuy(orderBuyProduct, username) == 1;
	}

	// 모든 상품의 10% 적립금 저장(update문)
	public void productReserve() {
		orderBuyDao.updateProductReserve();
	}

	// 장바구니에서 전체주문(적립금 누적, 적립금 사용시 누적감소)
	public void orderBuyCartInsert() {

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
