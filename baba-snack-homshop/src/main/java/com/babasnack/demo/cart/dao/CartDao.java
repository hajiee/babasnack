package com.babasnack.demo.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;

@Mapper
public interface CartDao {
	// 특정 회원의 장바구니 목록(상품 이미지 = product_saveimg) 가져오기
	@Select("select pp.product_saveimg from cart c inner join product_photo pp on c.pno = pp.pno where c.username=#{username};")
	public List<ProductPhoto> findByUsernameWithPphoto(String username);

	// 특정 회원의 장바구니 목록 가져오기
	@Select("select * from cart where username=#{username}")
	public List<Cart> findByUsername(String username);

	// 장바구니 추가
	@Insert("insert into cart values(#{pno}, #{username}, #{productCnt}, #{productPrice}, #{allPrice}, #{productName})")
	public Integer addCart(Cart cart);
	
	// 장바구니 추가용으로 product의 pno를 불러와 상품가격과 상품명을 가져올려고 만든 select문
	@Select("select * from product where pno=#{pno} and rownum=1")
	public Product findByPnoProduct(Long pno);

	// 장바구니 상품개수 합계
	@Select("select sum(product_cnt) from cart where username=#{username}")
	public Long allProductCntByUsername(String username);

	// 장바구니 상품가격 합계
	@Select("select sum(all_price) from cart where username=#{username}")
	public Long sumProductByUsername(String username);

	// 장바구니 전체 삭제
	@Delete("delete from cart where username=#{username}")
	public void deleteByUsername(String username);
}
