package com.babasnack.demo.product.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.babasnack.demo.entity.Product;
import com.babasnack.demo.entity.ProductPhoto;
import com.babasnack.demo.entity.Review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class ProductDto {
	@Autowired
	Category category;
	
	//상품등록
	@Data
	public static class WriteP{	
		private String productName;
		private String productNotice;
		private Long productStock;
		private Long productPrice;
		private Long productSize;
		private Category category;
		private List<MultipartFile> productPhoto;
		public Product toProduct() {
			Product product = new Product();
		    product.setProductName(this.productName);
		    product.setProductNotice(this.productNotice);
		    product.setProductStock(this.productStock);
		    product.setProductPrice(this.productPrice);
		    product.setProductSize(this.productSize);
		    // 나머지 필드 초기화
		    return product;
		}
	}
	
	//상품관리
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AminP{
		private Long pno;
		private Long productStock;
		private Category category;
	}
	
	//상품목록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ListP{
		private String productName;
		private Long productStock;
		private Long productPrice;
		private Long productSize;
		private List<ProductPhoto> productPhoto;
	}
	
	//상품상세
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
	    private List<String> productPhoto;
	    private List<Review> reviews;
	}
	
	//상품검색
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Search{
		private String productName;
	}
	
}
	
