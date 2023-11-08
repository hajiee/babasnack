package com.babasnack.demo.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartWithPhoto {
	private Long pno;
	private String username;
	private Long productCnt;
	private Long productPrice;
	private Long allPrice;
	private String productName;

	// ProductPhoto 객체
	private Long productImgNo;
	private String productImg; // 원본 이미지파일명
	private String productSaveImg; // 저장된 파일명
}
