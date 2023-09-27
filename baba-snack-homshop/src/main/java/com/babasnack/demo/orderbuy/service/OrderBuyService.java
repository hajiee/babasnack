package com.babasnack.demo.orderbuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.OrderBuy;
import com.babasnack.demo.entity.Product;
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

	

	// 주문상품 발송여부(관리자)
	public Boolean deliveryUpdate(String deliveryState, String username, Long ono) {
		OrderBuy orderBuy = orderBuyDao.findByUsernameAndOno(username, ono);
		if (orderBuy != null) {
			if (deliveryState != "배송준비")
				return false;
		}
		return orderBuyDao.updateDeliveryState(deliveryState, username, ono) == 1;
	}
}
