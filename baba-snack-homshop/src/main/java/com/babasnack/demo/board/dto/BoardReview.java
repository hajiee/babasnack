package com.babasnack.demo.board.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardReview {
	@Data
	public static class WriteBR{
		private Long bno;
		private String adminNotice;
	}
}
