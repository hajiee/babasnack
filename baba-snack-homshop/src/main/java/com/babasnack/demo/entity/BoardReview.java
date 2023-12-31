package com.babasnack.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReview {
	private Long brno;
	private Long bno;
	private String adminNotice;
	private LocalDate adminDate;
	private String admin;
}
