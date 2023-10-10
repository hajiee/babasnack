package com.babasnack.demo.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDto {
	//게시판목록
	@Data
	public static class ListB{
		private Long bno;
		private String title;
		private String boardWriter;
		private String boardDate;
		private Boolean boardState;
	}
	
	//문의, 공지등록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WriteB{
		private Long bno;
		private String title;
		private String boardNotice;
		private Boolean boardState;
		private Long boardCode;
	}
		
	//게시판관리
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AminB{
		private Long bno;
		private String title;
		private String boardDate;
		private Long boardCode;
	}
}
