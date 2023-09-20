package com.babasnack.demo.entity;

import java.util.List;

import com.babasnack.demo.product.dto.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private Long pno;
	private String productName;
	private String productNotice;
	private Long productStock;
	private Long productPrice;
	private Long productSize;
	private Long reserve;
	private Long productCnt;
	private Category category;
	
	// Product 엔티티에 사진 목록을 저장할 컬렉션 필드 추가
	// 해당 상품과 연관된 사진들을 저장하기 위한 컬렉션
	private List<ProductPhoto> photos;
    public List<ProductPhoto> getPhotos() {
        return photos;
    }
    public void setPhotos(List<ProductPhoto> photos) {
        this.photos = photos;
    }
}
