package com.babasnack.demo.orderdetail.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailDto {

	// 04-3 주문 내역 페이지
	// 07-2 주문상세 상품 목록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetail {
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

	// 주문상세 장바구니 상품
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OrderDetailCart {
		private String username;
		private Long productCnt;
		private Long allPrice;
		private Long productPrice;
		private String productName;
		private Long pno;
	}

	// 09 구매완료 페이지(배송 정보)
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetailDelivery {
		private Long dno;
		private String username;
		private String name;
		private Long pnoTell;
		private String baseDelivery;
		private String addDelivery;
	}

	// 10-3 회원 주문 내역리스트(관리자)
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetailAdmin {
		private String username;
		private Long pno;
		private Long buy_cnt;
		private Long dno;
		private Long ono;
	}

	// 주문자 정보(9, 10-3)
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReadOrderDetailMember {
		private String username;
		private Long pnoTell;
		private String email;
		private Long odno;
	}

}
