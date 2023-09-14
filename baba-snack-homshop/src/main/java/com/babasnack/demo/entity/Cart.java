package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
	private Long pno;
	private String username;
	private Long productCnt;
	private Long productPrice;
	private Long allPrice;
	private String productName;
}
