package com.babasnack.demo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Delivery {
	private Long dno;
	private String username;
	private String name;
	private Long pnoTell;
	private String baseDelivery;
	private String addDelivery;
}
