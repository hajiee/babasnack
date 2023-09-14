package com.babasnack.demo.entity;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderBuy {
	private Long ono;
	private Long buyCnt;
	private LocalDateTime orderDay;
	private Long allPrice;
	private String deliveryState;
	private String  baseDelivery;
	private Long allReserve;
	private Long dno;
	private String username;
	private Long payno;
}