package com.babasnack.demo.board.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import com.babasnack.demo.board.dto.BoardReviewDto;

@Mapper
public interface BoardReviewDao {
	@Insert("INSERT INTO board_review (brno, bno, admin_notice) VALUES (#{brno}, #{bno}, #{adminNotice})")
	@SelectKey(statement = "SELECT board_review_seq.nextval FROM dual", before = true, keyProperty = "brno",  resultType = Long.class)
	public void addBoardReview(BoardReviewDto.WriteBR writeBR);
}
