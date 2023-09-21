package com.babasnack.demo.orderbuy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.OrderBuy;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto;

@Mapper
public interface OrderBuyDao {
	// 주문 정보 저장
	@Insert("insert into order_buy(ono, buy_cnt, order_day, all_price, delivery_state, base_delivery, all_reserve, dno, username, payno) values(#{ono}, #{buyCnt}, #{orderDay}, #{allPrice}, '배송준비', #{baseDelivery}, #{allReserve}, #{dno}, #{username}, #{payno})")
	public Integer addOrderBuy(OrderBuyDto.OrderBuyProduct orderBuyProduct);
	
	// 주문 상품 정보
	@Select("select * from order_buy where username=#{username} and ono=#{ono}")
	public OrderBuy findByUsernameAndOno(String username, Long ono);
	
}
