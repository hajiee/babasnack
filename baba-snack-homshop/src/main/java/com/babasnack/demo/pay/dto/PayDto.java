package com.babasnack.demo.pay.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PayDto {

	// 결제방법
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PayDetail {
		private Long payno;
		private String payMent;
	}
}
