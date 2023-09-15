package com.babasnack.demo.orderdetail.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.entity.ProductPhoto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailDto {
	// 주문상세 상품 목록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetail {	
		private Long odno;
		private Long allPrice;
		private Long buyCnt;
		private Long productPrice;
		private String productName;
		private String productSaveimg;
		private Long ono;
		private Long pno;
	}
	
	// 주문자 정보
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetailMember {	
		private String username;
		private Long pnoTell;
		private String email;
	}
}
