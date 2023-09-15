package com.babasnack.demo.product.dto;

import java.util.List;

import com.babasnack.demo.entity.ReviewPhoto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDto {
	@Data
	public static class WritePR{
		private String reviewNotice;
		private Boolean star;
		private Long pno;
		private List<ReviewPhoto> reviewPhoto;
	}
}
