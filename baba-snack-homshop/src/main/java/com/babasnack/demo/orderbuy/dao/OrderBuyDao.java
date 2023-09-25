package com.babasnack.demo.orderbuy.dao;

import org.apache.ibatis.annotations.Delete;
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
	public Integer addOrderBuy(OrderBuyDto.OrderBuyProduct orderBuyProduct, String username);

	// 주문 상품 정보(관리자 확인용)
	@Select("select * from order_buy where username=#{username} and ono=#{ono}")
	public OrderBuy findByUsernameAndOno(String username, Long ono);

	// 적립금 계산

	// 주문 후 상품 수량 감소
	@Update("update product set product_stock=product_stock-#{productStock} where pno=#{pno}")
	public Integer decreaseProductStock(Long productStock, Long pno);

	// 주문 후 장바구니 전체 삭제
	@Delete("delete from cart where username=#{username}")
	public void deleteByUsername(String username);
}
