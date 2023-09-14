package com.babasnack.demo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
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
