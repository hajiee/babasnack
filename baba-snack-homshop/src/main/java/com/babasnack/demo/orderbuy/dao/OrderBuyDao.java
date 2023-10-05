package com.babasnack.demo.orderbuy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto.ReadOrderDetailByOB;

@Mapper
public interface OrderBuyDao {
	// 주문 정보 저장
	@Insert("insert into order_buy(ono, buy_cnt, order_day, all_price, delivery_state, base_delivery, all_reserve, dno, username, payno) values(order_buy_seq.nextval, #{buyCnt}, #{orderDay}, #{allPrice}, '배송중', #{baseDelivery}, #{allReserve}, #{dno}, #{username}, #{payno})")
	public Integer addOrderBuy(OrderBuyDto.OrderBuyProduct orderBuyProduct, String username);

	// 장바구니 주문정보 저장
	@Insert("insert into order_buy(ono, buy_cnt, order_day, all_price, delivery_state, base_delivery, all_reserve, dno, username, payno) values(order_buy_seq.nextval, #{buyCnt}, #{orderDay}, #{allPrice}, '배송중', #{baseDelivery}, #{allReserve}, #{dno}, #{username}, #{payno})")
	public Integer addOrderBuyCart(OrderBuyDto.OrderBuyCart orderBuyCart, String username);

	// 모든 상품가격을 적립금 10%로 저장(update문으로)
	@Update("update product set reserve = product_price*0.1")
	public void updateProductReserve();

	// 적립금 계산(장바구니에 들어간 모든 상품 적립금을 주문 테이블인 총적립금에 저장)

	// 적립금 누적(적립금 초기상태일 때)

	// 적립금 누적계산

	// 적립금 사용 후 계산(update문으로)

	// 적립금

	// 탈퇴시 해당회원 적립금 전부 다 삭제
	@Delete("delete from reserve where username=#{username}")
	public void deleteReserveByUsername(String username);

	// 주문 후 상품 수량 감소
	@Update("update product set product_stock=product_stock-#{productStock} where pno=#{pno}")
	public Integer decreaseProductStock(OrderBuyDto.ProductOrderBuyDto productOrderBuyDto);

	// 주문 후 상품 수량 감소(상품 테이블 pno 확인용)
	@Select("select * from product where pno=#{pno} and rownum=1")
	public OrderBuyDto.ProductOrderBuyDto findProductById(Long pno);

	// 주문 후 상품 수량 감소(상품+주문+주문상세 3테이블 inner join, 주문상세 테이블 odno 확인용)
	@Select("select * from order_buy ob inner join order_detail od on ob.ono = od.ono inner join product p on p.pno=od.pno where od.odno=#{odno}")
	public List<ReadOrderDetailByOB> findOrderDetailByOdno(Long odno);

	// 주문 후 장바구니 전체 삭제
	@Delete("delete from cart where username=#{username}")
	public void deleteCartByUsername(String username);

	// 주문 상품 정보(관리자 확인용, 주문정보 확인용)
	@Select("select * from order_buy where username=#{username} and ono=#{ono}")
	public OrderBuyDto.OrderBuyProduct findByUsernameAndOno(String username, Long ono);

	// 주문상품 발송여부(관리자 전용)
	@Update("update order_buy set delivery_state=#{deliveryState} where username=#{username} and ono=#{ono}")
	public Integer updateDeliveryState(String deliveryState, String username, Long ono);
}
