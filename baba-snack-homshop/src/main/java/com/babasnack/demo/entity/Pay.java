package com.babasnack.demo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pay {
	private Long payno;
	private String payMent;
}