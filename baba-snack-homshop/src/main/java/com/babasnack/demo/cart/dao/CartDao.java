package com.babasnack.demo.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.cart.dto.CartWithPhoto;
import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Delivery;
import com.babasnack.demo.entity.ProductPhoto;

@Mapper
public interface CartDao {
	// 특정 회원의 장바구니+상품이미지 목록 가져오기(list) = 완료
	@Select("select c.username, c.pno, c.product_cnt, c.product_price, c.all_price, c.product_name, pp.product_img, pp.product_imgno, pp.product_saveimg from product_photo pp inner join cart c on pp.pno = c.pno where username=#{username}")
	public List<CartWithPhoto> findByUsername(String username);

	// 특정 회원의 장바구니 비어있는지, 아닌지 확인용 = 완료
	@Select("select * from cart where username=#{username} and rownum=1")
	public Cart findByUsernameFromCart(String username);

	// 특정 회원의 배송지 주소 비어있는지, 아닌지 확인용 = 완료
	@Select("select * from delivery where username=#{username}")
	public Delivery findByUsernameFromDelivery(String username);

	// 장바구니 추가
	@Insert("insert into cart values(#{username}, #{pno}, #{productCnt}, #{productPrice}, #{allPrice}, #{productName})")
	public Integer addCart(Cart cart);

	// 장바구니 추가용으로 product의 pno를 불러와 상품가격과 상품명을 가져올려고 만든 select문
//	@Select("select * from product where pno=#{pno} and rownum=1")
//	public CartDto.ProductDto findByPnoProduct(Long pno);

	// 장바구니 상품개수 합계
	@Select("select sum(product_cnt) from cart where username=#{username}")
	public Long allProductCntByUsername(String username);

	// 장바구니 상품가격 합계
	@Select("select sum(all_price) from cart where username=#{username}")
	public Long sumProductByUsername(String username);

	// 장바구니 전체 삭제 = 완료
	@Delete("delete from cart where username=#{username}")
	public void deleteByUsername(String username);
}
