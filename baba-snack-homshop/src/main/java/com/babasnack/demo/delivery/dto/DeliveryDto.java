package com.babasnack.demo.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeliveryDto {
	// entity
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeliveryEntity {
		private Long dno;
		private String username;
		private String name;
		private Long pnoTell;
		private String baseDelivery;
		private String addDelivery;
	}

	// 회원 dto entity
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MemberEntity {
		private String username;
		private Long pnoTell;
	}

	// 04-2 배송지 구상

	// 08-1 결제 페이지 구상
}
