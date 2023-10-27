package com.babasnack.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	private BigDecimal reserve;
	private Long productCnt;
	private Category category;
	private LocalDate productDay = LocalDate.now();
	
	// Product 엔티티에 사진 목록을 저장할 컬렉션 필드 추가
	// 해당 상품과 연관된 사진들을 저장하기 위한 컬렉션
	private List<ProductPhoto> photos;
    
    public List<ProductPhoto> getPhotos() {
        return photos;
    }
    
    public void setPhotos(List<ProductPhoto> photos) {
        this.photos = photos;
    }
    
    public void setImageUrls(List<ProductPhoto> imageUrls) {
    	this.photos=imageUrls;
    }
    
	public void calculateReserve() {
        if (productPrice != null) {
            BigDecimal price = new BigDecimal(productPrice);
            BigDecimal tenPercent = price.multiply(BigDecimal.valueOf(0.1));
            reserve = tenPercent;
        } else {
            reserve = null; // 가격이 없을 경우 reserve 필드에 null 할당
        }
    }
}