package com.babasnack.demo.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.cart.dto.CartWithPhoto;
import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Delivery;
import com.babasnack.demo.entity.Product;
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

	// === 장바구니에 상품 추가 =========================================
	// 아이디와 상품번호로 장바구니 검색 - 추가한 상품이 장바구니에 등록된 상품인지 확인할 목적
	@Select("select * from cart where username=#{username} and pno=#{pno}")
	public Cart findByUsernameAndPno(String username, Long pno);

	// 장바구니 추가
	@Insert("insert into cart values(#{username}, #{pno}, #{productCnt}, #{productPrice}, #{allPrice}, #{productName})")
	public Integer addCart(Cart cart);

	// 장바구니의 상품 개수 증가
	@Update("update cart set product_cnt= product_cnt + #{productCnt}, all_price=all_price+product_price where pno=#{pno} and username=#{username}")
	public Integer increase(Long productCnt, Long pno, String username);

	// 상품 pno로 찾아서 장바구니에 상품 추가할 용도
	@Select("select * from product where pno=#{pno} and rownum=1")
	public Product findByIdFromProduct(Long pno);

	// 장바구니 상품 개수를 증가할 때 재고량을 확인하기 위해 사용한다
	@Select("select product_stock from product where pno=#{pno} and rownum=1")
	public Long findProducStockById(Long pno);
	// =============================================================

	// 장바구니 상품개수 합계
	@Select("select sum(product_cnt) from cart where username=#{username}")
	public Long allProductCntByUsername(String username);

	// 장바구니 상품가격 합계
	@Select("select sum(all_price) from cart where username=#{username}")
	public Long sumProductByUsername(String username);

	// 장바구니 전체 삭제 = 완료
	@Delete("delete from cart where username=#{username}")
	public void deleteByUsername(String username);

	// 상품 dto 테스트용
	@Select("select * from product where pno=#{pno}")
	public Product findByPnoProduct(Long pno);
}
