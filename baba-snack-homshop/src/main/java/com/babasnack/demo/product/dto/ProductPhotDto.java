package com.babasnack.demo.product.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPhotDto {
	@Data
	@AllArgsConstructor
	public static class addProductPhoto {
		private Long productImgNo;
		private String productImg;
		private String productSaveImg;
		private List<String> productImgUrl;

		public String getSavedFilename() {
			return productSaveImg;
		}

		public void setSavedFilename(String savedFilename) {
			this.productSaveImg = savedFilename;
		}

	}
}
