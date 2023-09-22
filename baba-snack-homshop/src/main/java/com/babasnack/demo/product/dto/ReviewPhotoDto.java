package com.babasnack.demo.product.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewPhotoDto {
	@Data
	@AllArgsConstructor
	public static class saveReviewPhoto{
		private Long reviewImgNo;
		private String reviewImg;
		private String reviewSaveImg;
		private String reviewImgUrl;
		
		public String getSavedFilename() {
			return reviewSaveImg;
		}

		public void setSavedFilename(String savedFilename) {
			this.reviewSaveImg = savedFilename;
		}
	}
}
