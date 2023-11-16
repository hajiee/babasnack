package com.babasnack.demo.board.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.babasnack.demo.board.dto.BoardReviewDto.WriteBR;
import com.babasnack.demo.board.service.BoardReviewService;

@Secured("ROLE_ADMIN")
@Controller
public class BoardReviewController {
	@Autowired
    private BoardReviewService boardReviewService;

    // 리뷰 작성 화면을 보여줄 GET 메서드
	@GetMapping("/board/board-read/{bno}/write-boardRev")
    public String boardReviewWriteForm(Model model, @PathVariable Long bno) {
		return "board/board-read";
    }

    // 리뷰 작성을 처리할 POST 메서드
	@PostMapping("/board/board-read/{bno}/add-boardRev")
	public String saveBoardReview(@PathVariable Long bno, @ModelAttribute WriteBR writeBR) {
        // 게시글 번호(bno)와 리뷰 내용(writeBR)을 사용하여 리뷰를 작성하는 코드 작성
        boardReviewService.addBoardReviews(writeBR);
        return "redirect:/board/board-read/" + bno; // 작성 후 해당 게시글 상세 페이지로 이동
    }
}
