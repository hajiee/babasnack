package com.babasnack.demo.cart.dto;

import java.util.*;


import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDto {

	// 장바구니 상품 목록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadCart {
		private List<CartWithPhoto> cart;
		private Long productCnt;
		private Long allPrice;
	}

	// 장바구니 상품 전체삭제
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeleteAllCart {
		private String username;
	}
}