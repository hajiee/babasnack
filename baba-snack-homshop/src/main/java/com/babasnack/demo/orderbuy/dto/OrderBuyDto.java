package com.babasnack.demo.orderbuy.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBuyDto {

	// 주문 상품정보
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OrderBuyProduct {
		private Long ono;
		private Long buyCnt;
		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime orderDay;
		private Long allPrice;
		private String deliveryState;
		private String  baseDelivery;
		private Long allReserve;
	}
}
