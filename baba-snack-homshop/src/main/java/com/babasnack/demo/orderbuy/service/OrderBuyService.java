package com.babasnack.demo.orderbuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.OrderBuy;
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
}
