package com.babasnack.demo.orderdetail.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.babasnack.demo.entity.OrderDetail;

@Mapper
public interface OrderDetailDao {
	// 주문상세 정보 저장(작성중)
	@Insert("insert into order_buy values(#{odno}, #{allPrice}, #{username}, #{buyCnt}, #{productPrice}, #{productName}, #{productSaveimg}, #{ono}, #{pno})")
	public Integer addOrderDetail(OrderDetail orderDetail);
}
