package com.babasnack.demo.board.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.babasnack.demo.board.dto.BoardReviewDto;

@Mapper
public interface BoardReviewDao {
	@Insert("INSERT INTO board_review (brno, bno, admin_notice) VALUES (board_review_seq.nextval, #{bno}, #{adminNotice})")
	public void addReview(BoardReviewDto.WriteBR writeBR);
}
