package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPhoto {
	private Long productImgNo;
    private Long pno;
    private String productImg; // 원본 이미지파일명
    private String productSaveImg; // 저장된 파일명
    private String productImgUrl; // 이미지 URL
    private Product product;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    
    // 생성자를 통해 pno 값을 전달 받음
    public ProductPhoto(Long pno, String productImg, String productSaveImg) {
    	this.pno = pno;
    	this.productImg = productImg;
    	this.productSaveImg = productSaveImg;
    }

    public void updateItemImg(String oriImgName, String imgName) {
        this.productImg = oriImgName;
        this.productSaveImg = imgName;
    }

    public String getSavedFilename() {
        return productSaveImg;
    }
}
