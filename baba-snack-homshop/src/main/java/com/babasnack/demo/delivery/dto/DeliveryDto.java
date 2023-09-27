package com.babasnack.demo.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeliveryDto {
	// entity
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeliveryAddress {
		private Long dno;
		private String username;
		private String name;
		private Long pnoTell;
		private String baseDelivery;
		private String addDelivery;
	}
	
	
	// 04-2 배송지 구상
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class updateDeliveryAddress {
		private Long dno;
		private String name;
		private Long pnoTell;
		private String baseDelivery;
		private String addDelivery;
	}

	// 08-1 결제 페이지 구상
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PayDeliveryAddress {
		private Long dno;
		private String name;
		private Long pnoTell;
		private String baseDelivery;

	}
}
