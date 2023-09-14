package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPhoto {
	private Long rno;
	private Long reviewImgNo;
	private String reviewImg;
	private String reviewSaveImg;
}
