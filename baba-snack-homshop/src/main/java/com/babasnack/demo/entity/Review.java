package com.babasnack.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
	private Long rno;
	private LocalDateTime reviewDate;
	private String reviewNotice;
	private Boolean star;
	private Long pno;
	private String reviewWrite;
}
