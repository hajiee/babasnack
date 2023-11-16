package com.babasnack.demo.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.board.dao.BoardReviewDao;
import com.babasnack.demo.board.dto.BoardReviewDto;

@Service
public class BoardReviewService {
	@Autowired
	private BoardReviewDao boardReviewDao;

	public void addBoardReviews(BoardReviewDto.WriteBR writeBR) {
	        boardReviewDao.addBoardReview(writeBR);
	    }
}
