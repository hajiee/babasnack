package com.babasnack.demo.cart.dto;

import java.util.*;

import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.ProductPhoto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDto {
	
	// 장바구니 상품 목록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadCart {	
		private List<ProductPhoto> productPhoto;
		private Long pno;
		private Long productCnt;
		private Long productPrice;
		private Long allPrice;
		private String productName;
	}
	
	// 장바구니 상품 삭제
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeleteAllCart {
		private String username;
	}
}