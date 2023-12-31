package com.babasnack.demo.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.babasnack.demo.entity.Board;

@Mapper
public interface BoardAdminDao {
	//번호로 조회
    @Select("SELECT * FROM board WHERE bno = #{bno}")
    public Board findByBoard(Long bno);
    
    //관리자용 게시글 열람
    @Select("SELECT * FROM board")
    public List<Board> findAllForAdmin();
}
