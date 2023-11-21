package com.babasnack.demo.product.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductDto {
    // 상품 등록
    @Data
    @AllArgsConstructor
    public static class WriteP {
        private String productName;
        private String productNotice;
        private Long productStock;
        private Long productPrice;
        private Long productSize;
        private Category category;
        private BigDecimal reserve;
        private List<ProductPhoto> productPhotos;

        public Product toProduct() {
            Product product = new Product();
            product.setProductName(this.productName);
            product.setProductNotice(this.productNotice);
            product.setProductStock(this.productStock);
            product.setProductPrice(this.productPrice);
            product.setProductSize(this.productSize);
            product.setCategory(this.category);
            product.setReserve(this.reserve);
            // 나머지 필드 초기화
            return product;
        }
    }
    
    // 상품 관리
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AminP {
        private Long pno;
        private String productName;
        private Long productStock;
        private Category category;
        private LocalDate productDay;;
    }
    
    // 상품 목록
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListP {
    	private Long pno;
        private String productName;
        private Long productStock;
        private Long productPrice;
        private Long productSize;
        private Category category;
        private List<ProductPhoto> productPhoto;
    }
    
    // 상품 상세
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReadP {
        private Long pno;
        private String productName;
        private String productNotice;
        private Long productStock;
        private Long productPrice;
        private Long productSize;
        private Long reviewCount;
        private Double reviewStar;
        private List<ProductPhoto> productPhoto;
        private List<Review> reviews;
    }
    
    // 상품 검색
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Search {
        private String productName;
    }
}