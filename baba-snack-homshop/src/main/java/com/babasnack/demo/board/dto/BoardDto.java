package com.babasnack.demo.board.dto;

import java.time.LocalDate;
import java.util.List;

import com.babasnack.demo.entity.BoardReview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardDto {
	//게시판목록
	@Data
	public static class ListB{
		private Long bno;
		private String title;
		private String boardWriter;
		private LocalDate boardDate;
		private boolean boardState;
	}
	
	//문의, 공지등록
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WriteB {
	    private Long bno;
	    private String title;
	    private String boardNotice;
	    private String boardWriter;
	    private boolean boardState;
	    private Integer boardCode;
	}
		
	//게시판관리
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class AminB{
			private Long bno;
			private String title;
			private LocalDate boardDate;
			private Integer boardCode;
			private List<BoardReview> boardReview;
		}
}
