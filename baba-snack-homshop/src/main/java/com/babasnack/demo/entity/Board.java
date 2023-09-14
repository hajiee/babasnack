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
public class Board {
	private Long bno;
	private String title;
	private String boardNotice;
	private String boardWriter;
	private LocalDateTime boardDate;
	private Boolean boardState;
	private Long boardCode;
}
