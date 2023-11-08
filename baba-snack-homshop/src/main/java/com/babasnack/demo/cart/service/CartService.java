package com.babasnack.demo.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.cart.dao.CartDao;
import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.cart.dto.CartWithPhoto;
import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Delivery;
import com.babasnack.demo.entity.ProductPhoto;

@Service
public class CartService {
	@Autowired
	private CartDao cartDao;

	// 장바구니 목록과 가격 합계를 CartDto.ReadCart2에 담아 리턴	
	@Transactional(readOnly = true)
	public CartDto.ReadCart read2(String username) {
		List<CartWithPhoto> cart= cartDao.findByUsername(username);
		Long productCnt = cartDao.allProductCntByUsername(username);
		Long allPrice = cartDao.sumProductByUsername(username);
		
		return new CartDto.ReadCart(cart, productCnt, allPrice);
	}


	// 특정 회원의 장바구니 비어있는지, 아닌지 확인용
	public Cart findByUsernameFromCart(String username) {
		return cartDao.findByUsernameFromCart(username);
	}

	// 특정 회원의 배송지 주소 비어있는지, 아닌지 확인용
	public Delivery findByUsernameFromDelivery(String username) {
		return cartDao.findByUsernameFromDelivery(username);
	}

	// 장바구니 추가(상품이미지 테이블 상품이미지 저장파일 + 상품 테이블 가격, 상품명)
//	public Boolean add(Long pno, String username) {
//		CartDto.ProductDto product = cartDao.findByPnoProduct(pno);
//		Cart newCart = new Cart(pno, username, 1L, product.getProductPrice(), product.getProductPrice(),
//				product.getProductName());
//		cartDao.addCart(newCart);
//		return true;
//	}

	// 장바구니 전체삭제
	public void deleteAll(String username) {
		cartDao.deleteByUsername(username);
	}
}
