package com.babasnack.demo.orderdetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.OrderDetail;
import com.babasnack.demo.entity.Product;
import com.babasnack.demo.orderdetail.dao.OrderDetailDao;
import com.babasnack.demo.orderdetail.dto.OrderDetailDto;
import com.babasnack.demo.orderdetail.dto.OrderDetailDto.ReadOrderDetail;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailDao orderDetailDao;

	// 주문상세 정보 저장
	public Boolean add(OrderDetail orderDetail, String username) {
		return orderDetailDao.addOrderDetail(orderDetail, username) == 1;
	}

	// 회원 주문자 정보(관리자)
	public OrderDetailDto.ReadOrderDetailMember orderDetailMember(String username, Long odno) {
		return orderDetailDao.findByUsernameAndOdno(username, odno);
	}

	// 회원 주문목록(관리자)
	public List<OrderDetailDto.ReadOrderDetailAdmin> orderDetailAdmin(String username, Long ono) {
		return orderDetailDao.orderDetailAdmin(username, ono);
	}

	// 주문 후 상품 수량 감소
	public Boolean decreaseProduct(Product product, Long pno) {
		product = orderDetailDao.findProductById(pno);
		if (product.getProductStock() < 0)
			return false;
		return orderDetailDao.decreaseProductStock(product) == 1;
	}

	// 주문 후 상품 수량 감소(odno 출력용)
	public List<ReadOrderDetail> findOrderDetailByOdno(Long odno) {
		return orderDetailDao.findOrderDetailByOdno(odno);
	}

}
