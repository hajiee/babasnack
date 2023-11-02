package com.babasnack.demo.orderbuy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale.Category;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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

	// 주문용 상품 테이블
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ProductOrderBuyDto {
		private Long pno;
		private String productName;
		private String productNotice;
		private Long productStock;
		private Long productPrice;
		private Long productSize;
		private Long reserve;
		private Long productCnt;
		private Category category;
		private LocalDateTime productDay;
	}

	// 04-3 주문 내역 페이지
	// 07-2 주문상세 상품 목록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetailByOB {
		private Long odno;
		private Long allPrice;
		private String username;
		private Long buyCnt;
		private Long productPrice;
		private String productName;
		private String productSaveimg;
		private Long ono;
		private Long pno;
	}

	// 장바구니 주문
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CartByOrderBuy {
		private Long pno;
		private String username;
		private Long productCnt;
		private Long productPrice;
		private Long allPrice;
		private String productName;
	}

	// 적립금
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReserveByOrderBuy {
		private String username;
		private Long firstReserve;
		private Long reservePlus;
		private LocalDate reserveDay = LocalDate.now();
		private Long ono;
		private Long amount;
	}
	
	// 배송지
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeliveryByOrderBuy {
		private String username;
		private String name;
		private Long pnoTell;
		private String baseDelivery;
	}

	// 06-3 상품 상세설명 페이지

	// 07-1 장바구니 구상(주문하기)

	// 07-2 주문상세 구상
}
