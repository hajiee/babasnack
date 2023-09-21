package com.babasnack.demo.orderdetail.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.OrderBuy;
import com.babasnack.demo.entity.OrderDetail;

@Mapper
public interface OrderDetailDao {
	// 주문상세 정보 저장
	@Insert("insert into order_detail(odno, all_price, username, buy_cnt, product_price, product_name, product_saveimg, ono, pno) values(#{odno}, #{allPrice}, #{username}, #{buyCnt}, #{productPrice}, #{productName}, #{productSaveimg}, #{ono}, #{pno})")
	public Integer addOrderDetail(OrderDetail orderDetail);

	// 주문상품 상세 정보
	@Select("select * from order_detail where username=#{username} and odno=#{odno}")
	public OrderBuy findByUsernameAndOno(String username, Long odno);


}
