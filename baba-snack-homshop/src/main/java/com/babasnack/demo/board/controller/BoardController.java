package com.babasnack.demo.board.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.board.dto.BoardDto;
import com.babasnack.demo.board.dto.BoardDto.WriteB;
import com.babasnack.demo.board.dto.BoardPage;
import com.babasnack.demo.board.service.BoardAdminService;
import com.babasnack.demo.board.service.BoardService;
import com.babasnack.demo.entity.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardAdminService boardAdminService;
	
	@GetMapping("/board/board-list")
	public ModelAndView boardList(@RequestParam(defaultValue="1") Long pageno) {
		BoardPage boardPage = boardService.page(pageno);
		return new ModelAndView("board-list").addObject("boardPage", pageno);
	}
	
	@GetMapping("/board/board-read/{title}")
    public String boardDetail(Model model, @PathVariable String name) {
		Board board = boardService.findbyTitle(name);

        // 게시글 상세 정보를 모델에 추가
        model.addAttribute("board", board);

        return "board/board-read";
    }
	
	//글작성
	@GetMapping("/board/board-write")
	public String boardWriteForm() {
	        return "board/board-write";
	    }

	@PostMapping("/board/board-write")
	public String boardWrite(@ModelAttribute BoardDto.WriteB writeB, Principal principal) {
		String username = principal.getName(); // 사용자의 이름을 얻음 (일반적으로 사용자명 또는 ID)
		// 간단한 예제로, 사용자명이 "admin"일 경우에만 공지사항(boardState) 설정 가능하도록 함
	    if ("admin".equals(username)) {
	        // 관리자인 경우에만 공지사항(boardState)을 true로 설정
	        writeB.setBoardState(true);
	    }else {
	    	// 일반 사용자인 경우에는 공지사항(boardState)을 false로 설정
	        writeB.setBoardState(false);
	    }
		boardService.addBoard(writeB);
	       
	    return "redirect:/board/board-list";
	}
	
	
	@GetMapping("/board/{bno}/board-edit")
    public String editBoardForm(Model model, @PathVariable Long bno) {
        Board board = boardAdminService.findbybNoForAdmin(bno);

        // 수정할 게시글 정보를 모델에 추가
        model.addAttribute("board", board);

        return "board/board-edit"; // 수정 화면으로 이동
    }
	
	@PostMapping("/board/{bno}/board-edit")
	public String editBoard(@ModelAttribute WriteB updatedBoard, @RequestParam("password") Long password) {
	    Long bno = updatedBoard.getBno();

	    // 입력한 비밀번호와 게시글의 비밀번호를 비교하여 일치하면 수정 가능
	    if (boardService.checkboardCode(bno, password)) {
	        boardService.updateBoard(updatedBoard);
	        return "redirect:/board/board-list";
	    } else {
	        // 비밀번호가 일치하지 않는 경우에는 오류 페이지 또는 리다이렉트 등을 처리할 수 있음
	        return "error"; // 비밀번호 불일치를 나타내는 뷰 페이지로 이동하거나 오류 페이지로 리다이렉트 등
	    }
	}

	@PostMapping("/board/{bno}/delete")
	public String deleteBoard(@PathVariable Long bno, @RequestParam("password") Long password) {
	    // 입력한 비밀번호와 게시글의 비밀번호를 비교하여 일치하면 삭제 가능
	    if (boardService.checkboardCode(bno, password)) {
	        boardService.deleteBoard(bno);
	        return "redirect:/board/board-list";
	    } else {
	        // 비밀번호가 일치하지 않는 경우에는 오류 페이지 또는 리다이렉트 등을 처리할 수 있음
	    	return "error"; // 비밀번호 불일치를 나타내는 뷰 페이지로 이동하거나 오류 페이지로 리다이렉트 등
	    }
	}

}
