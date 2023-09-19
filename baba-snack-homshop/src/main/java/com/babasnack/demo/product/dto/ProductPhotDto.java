package com.babasnack.demo.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class ProductPhotDto {
	@Data
	@AllArgsConstructor
	public static class saveProductPhoto{
	private Long productImgNo;
	private String productImg;
	private String productSaveImg;
	private String productImgUrl;
	}
}
