package com.babasnack.demo.board.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
		return new ModelAndView("board/board-list").addObject("boardPage", boardPage);
	}
	
	@GetMapping("/board/board-read/{bno}")
    public String boardDetail(Model model, @PathVariable Long bno,@RequestParam(value = "password", required = false) Long password) {
		Board board = boardAdminService.findbybNoForAdmin(bno);

        // 게시글 상세 정보를 모델에 추가
        model.addAttribute("board", board);

        if (password != null && !boardService.checkBoardCode(bno, password)) {
            // 비밀번호가 일치하지 않는 경우에 대한 처리 로직
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "forward:/board/board-list";
        }
        return "board/board-read/{bno}";
    }
	
	//글작성
	@GetMapping("/board/board-write")
	public String boardWriteForm(Model model) {
	    model.addAttribute("writeB", new BoardDto.WriteB());
	    return "board/board-write";
	    }

	@PostMapping("/board/board-write")
	public String boardWrite(@ModelAttribute("writeB") @Validated(WriteB.class) BoardDto.WriteB writeB, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
	        // 유효성 검사 에러가 있는 경우 처리
	        return "board/board-write";
	    }
		
		String username = principal.getName(); // 사용자의 이름을 얻음 (일반적으로 사용자명 또는 ID)
		
		// 글 작성 시 입력한 암호(비밀번호) 설정
        writeB.setBoardCode(writeB.getBoardCode());
		
		//사용자명이 "admin"일 경우에만 공지사항(boardState) 설정가능
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

        return "board/{bno}/board-edit"; // 수정 화면으로 이동
    }
	
	@PostMapping("/board/{bno}/board-edit")
	public String editBoard(@ModelAttribute WriteB updatedBoard, @RequestParam("password") Long password, HttpServletRequest request) {
	    Long bno = updatedBoard.getBno();

	    // 입력한 비밀번호와 게시글의 비밀번호를 비교하여 일치하면 수정 가능
	    if (boardService.checkBoardCode(bno, password)) {
	        boardService.updateBoard(updatedBoard);
	        return "redirect:/board/{bno}/board-edit";
	    } else {
	    	// 비밀번호가 일치하지 않는 경우에 대한 처리 로직
	        request.setAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
	        return "forward:/board/board-read/{bno}";
	    }
	}

	@PostMapping("/board/{bno}/delete")
	public String deleteBoard(@PathVariable Long bno, @RequestParam("password") Long password, HttpServletRequest request) {
	    // 입력한 비밀번호와 게시글의 비밀번호를 비교하여 일치하면 삭제 가능
	    if (boardService.checkBoardCode(bno, password)) {
	        boardService.deleteBoard(bno);
	        return "redirect:/board/board-list";
	    } else {
	    	// 비밀번호가 일치하지 않는 경우에 대한 처리 로직
	        request.setAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
	    	return "foward:/board/{bno}/board-edit"; 
	    }
	}

}
