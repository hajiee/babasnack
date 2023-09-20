package com.babasnack.demo.orderbuy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.babasnack.demo.orderbuy.dto.OrderBuyDto;

@Mapper
public interface OrderBuyDao {
	// 주문 정보 저장
	@Insert("insert into order_buy values(#{ono}, #{buyCnt}, #{orderDay}, #{allPrice}, #{deliveryState}, #{baseDelivery}, #{allReserve}, #{dno}, #{username}, #{payno})")
	public Integer addOrderBuy(OrderBuyDto.OrderBuyProduct orderBuyProduct);
}
