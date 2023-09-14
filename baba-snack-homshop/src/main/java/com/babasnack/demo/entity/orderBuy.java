package com.babasnack.demo.entity;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class orderBuy {
	private Long ono;
	private Long buyCnt;
	private LocalDateTime orderDay = LocalDateTime.now();
	private Long allPrice;
	private String deliveryState;
	private String  baseDelivery;
	private Long allReserve;
	private Long dno;
	private String username;
	private Long payno;
}
