package com.babasnack.demo.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.cart.dao.CartDao;
import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.cart.dto.CartDto.ReadCart;
import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.product.dao.ProductDao;

@Service
public class CartService {
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao productDao;

	// 장바구니 목록과 가격 합계를 CartDto.ReadCart에 담아 리턴
	@Transactional(readOnly = true)
	public CartDto.ReadCart read(String username) {
		List<ProductPhoto> productPhoto = cartDao.findByUsernameWithPphoto(username);
		List<Cart> cart = cartDao.findByUsername(username);
		Long productCnt = cartDao.allProductCntByUsername(username);
		Long allPrice = cartDao.sumProductByUsername(username);

		return new CartDto.ReadCart(productPhoto, cart, productCnt, allPrice);
	}
	
	public Boolean add(Long pno, String username) {
		Product product = productDao.getProductById(pno); // productDao
		Cart newCart = new Cart(pno, username, 1L, product.getProductPrice(), product.getProductPrice(), product.getProductName());
		cartDao.addCart(newCart);
		
		return true; 
		
	}

	public void deleteAll(String username) {
		cartDao.deleteByUsername(username);
	}

}
