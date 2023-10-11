package com.babasnack.demo.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPhoto {
	private Long pno;
	private Long productImgNo;
	private String productImg;	// 원본 이미지파일명
	private String productSaveImg;	// 저장된 파일명
	
	@Value("${productImgUrl}")
	private List<String> productImgUrl;
	
	public void updateItemImg(String oriImgName, String imgName, List<String> imgUrl) {
        this.productImg = oriImgName;
        this.productSaveImg = imgName;
        this.productImgUrl = imgUrl;
	}

	public String getSavedFilename() {
		return productSaveImg;
	}
	
}

