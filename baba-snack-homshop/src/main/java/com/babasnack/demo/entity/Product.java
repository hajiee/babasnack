package com.babasnack.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private Long pno;
	private String productName;
	private String productNotice;
	private Long productStock;
	private Long productPrice;
	private Long productSize;
	private Long reserve;
}
