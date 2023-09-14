package com.babasnack.demo.entity;

import lombok.*;

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