package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPhoto {
	private Long pno;
	private Long productImgNo;
	private String productImg;
	private String productSaveImg;
}
