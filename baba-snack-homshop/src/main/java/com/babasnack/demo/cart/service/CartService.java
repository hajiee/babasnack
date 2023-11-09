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
import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;

@Service
public class CartService {
	@Autowired
	private CartDao cartDao;

	// 장바구니 목록과 가격 합계를 CartDto.ReadCart2에 담아 리턴
	@Transactional(readOnly = true)
	public CartDto.ReadCart read(String username) {
		List<CartWithPhoto> cart = cartDao.findByUsername(username);
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

	// 장바구니 구매 버튼(장바구니에 있는 상품이면 개수 증가, 없는 상품이면 담는다)
	public Boolean add(Long productCnt, Long pno, String username) {
		Cart cart = cartDao.findByUsernameAndPno(username, pno);

		if (cart != null) {
			// 장바구니에 상품이 있으면, 그 상품 개수 증가
			Long stock = cartDao.findProducStockById(pno);
			if (cart.getProductCnt() >= stock)
				return false;
			return cartDao.increase(productCnt, pno, username) == 1;
		} else {
			// 주문할 때 상품이 없으면 상품 추가
			Product product = cartDao.findByIdFromProduct(pno);
			Cart newCart = new Cart(pno, username, productCnt, product.getProductPrice(), product.getProductPrice(),
					product.getProductName());
			return cartDao.addCart(newCart) == 1;
		}
	}

	// 장바구니 전체삭제 = 완료
	public void deleteAll(String username) {
		cartDao.deleteByUsername(username);
	}

	// 상품 dto 테스트용
	@Transactional(readOnly = true)
	public CartDto.testProductDto readTestProduct(Long pno) {
		Product p = cartDao.findByPnoProduct(pno);
		return new CartDto.testProductDto(p.getPno(), p.getProductName(), p.getProductNotice(), p.getProductStock(),
				p.getProductPrice(), p.getProductSize());
	}
}
