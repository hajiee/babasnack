package com.babasnack.demo.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.babasnack.demo.board.service.BoardAdminService;
import com.babasnack.demo.entity.Board;

@Secured("ROLE_ADMIN")
@Controller
public class BoardAdminController {
	@Autowired
	private BoardAdminService boardAdminService;
	
	@GetMapping("/board/admin-board/board-read/{bno}")
    public String adminBoardDetail(Model model, @PathVariable Long bno) {
		Board board = boardAdminService.findbybNoForAdmin(bno);
		// 관리자용 게시글 상세 정보를 모델에 추가
        model.addAttribute("board", board);
        return "board/board-read/{bno}";
    }
}
