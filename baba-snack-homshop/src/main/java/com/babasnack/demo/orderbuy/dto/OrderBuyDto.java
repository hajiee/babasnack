package com.babasnack.demo.orderbuy.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBuyDto {
	// 주문 정보
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OrderBuyProduct {
		private Long ono;
		private Long buyCnt;
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime orderDay = LocalDateTime.now();
		private Long allPrice;
		private String deliveryState;
		private String baseDelivery;
		private Long allReserve;
		private Long dno;
		private String username;
		private Long payno;
	}

	
	
	// 06-3 상품 상세설명 페이지

	// 07-1 장바구니 구상(주문하기)
	
	// 07-2 주문상세 구상
}
