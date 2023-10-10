package com.babasnack.demo.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.babasnack.demo.board.service.BoardAdminService;
import com.babasnack.demo.entity.Board;

@Controller
public class BoardAdminController {
	@Autowired
	private BoardAdminService boardAdminService;
	
	@GetMapping("/board/board-read/{bno}")
    public String adminBoardDetail(Model model, @PathVariable Long bno) {
		Board board = boardAdminService.findbybNoForAdmin(bno);
		// 관리자용 게시글 상세 정보를 모델에 추가
        model.addAttribute("board", board);
        return "board/adminBoardDetail";
    }
    
	@GetMapping("/board/Admin-board")
    public String adminBoardList(Model model) {
        List<Board> boards = boardAdminService.getAllBoardsForAdmin();
        // 관리자용 게시글 목록을 모델에 추가
        model.addAttribute("boards", boards);
        return "board/admin-board";
    }
}
