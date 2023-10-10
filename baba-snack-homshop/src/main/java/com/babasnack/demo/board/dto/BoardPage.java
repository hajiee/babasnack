package com.babasnack.demo.board.dto;

import java.util.List;

import com.babasnack.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPage {
	private Long prev;
	private Long start;
	private Long end;
	private Long next;
	private Long pageno;
	private List<Board> boardes;
}
