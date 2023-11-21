package com.babasnack.demo.orderdetail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.babasnack.demo.cart.dto.CartWithPhoto;
import com.babasnack.demo.entity.Member;
import com.babasnack.demo.entity.OrderDetail;
import com.babasnack.demo.orderdetail.dto.OrderDetailDto;

@Mapper
public interface OrderDetailDao {
	// 주문상세 정보 저장
	@Insert("insert into order_detail(odno, all_price, username, buy_cnt, product_price, product_name, product_saveimg, ono, pno) values(order_detail_seq.nextval, #{allPrice}, #{username}, #{buyCnt}, #{productPrice}, #{productName}, #{productSaveimg}, #{ono}, #{pno})")
	public Integer addOrderDetail(OrderDetail orderDetail, String username);

	// 회원 정보 아이디로 찾기(member 테이블)
	@Select("select * from member where username=#{username}")
	@Result(property = "email", column = "ps_email")
	public Member findUsernameFromMember(String username);

	// 주문상품 상세 정보(회원, 관리자용)
	@Select("select m.username, m.pno_tell, m.ps_email from member m inner join order_detail od"
			+ " on m.username=od.username where od.username=#{username} and od.odno=#{odno}")
	public OrderDetailDto.ReadOrderDetailMember findByUsernameAndOdno(String username, Long odno);

	// 주문한 회원정보 상세 내역리스트(관리자 전용)
	@Select("select od.username, od.pno, od.buy_cnt, ob.dno from order_buy ob inner join order_detail od"
			+ " on ob.ono=od.ono where od.username=#{username} and ob.ono=#{ono}")
	public List<OrderDetailDto.ReadOrderDetailAdmin> orderDetailAdmin(String username, Long ono);

	// ==== 장바구니 목록 출력(주문상세용) ========================
	// 특정 회원의 장바구니+상품이미지 list로 목록 가져오기(주문상세용)
	@Select("select c.username, c.pno, c.product_cnt, c.product_price, c.all_price, c.product_name,"
			+ " pp.product_img, pp.product_imgno, pp.product_saveimg from product_photo pp"
			+ " inner join cart c on pp.pno = c.pno where username=#{username}")
	public List<CartWithPhoto> findByUsernameFromCartOD(String username);

	// 장바구니 상품개수 합계(주문상세용)
	@Select("select sum(product_cnt) from cart where username=#{username}")
	public Long allProductCntByUsernameFromCartOD(String username);

	// 장바구니 상품가격 합계(주문상세용)
	@Select("select sum(all_price) from cart where username=#{username}")
	public Long sumProductByUsernameFromCartOD(String username);
	// ====================================================

	// 해당 사용자가 주문한 상품인지 확인
	@Select("SELECT COUNT(*) FROM order_detail WHERE username = #{username} AND pno = #{pno}")
	public boolean isBuyer(String username, Long pno);
}
