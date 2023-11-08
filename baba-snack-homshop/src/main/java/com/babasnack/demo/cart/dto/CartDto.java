package com.babasnack.demo.cart.dto;

import java.util.*;

import com.babasnack.demo.entity.Cart;

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
		private Long productPrice;
		private Long allPrice;
	}

	// 장바구니 상품 전체삭제
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeleteAllCart {
		private String username;
	}

	// 상품 사진
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ProductPhotoDto {
		private Long pno;
		private Long productImgNo;
		private String productImg; // 원본 이미지파일명
		private String productSaveImg;
	}


}