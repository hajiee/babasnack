package com.babasnack.demo.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.board.dto.BoardDto;
import com.babasnack.demo.entity.Board;

@Mapper
public interface BoardDao {
	//글 저장
	@Insert("INSERT INTO board (bno, title, board_notice, board_state, board_code) " +
            "VALUES (board_seq.nextval, #{title}, #{boardNotice}, #{boardState}, #{boardCode})")
    public Long addBoard(BoardDto.WriteB writeB);
	
	//글 수정
 	@Update("UPDATE board SET title = #{title}, " +
 	        "board_notice = #{boardNotice}, " +
 	        "board_state = #{boardState}, " +
 	        "board_code = #{boardCode} WHERE bno = #{bno}")
 	public Long updateBoard(BoardDto.WriteB UpBoardWrite);
	
	//글 삭제
    @Delete("DELETE FROM board WHERE bno = #{bno}")
    public Integer deleteBoard(Long bno);
	
	//글 목록
	@Select("SELECT * FROM board")
    public List<Board> FindAll();
	
	//제목으로 조회
    @Select("SELECT * FROM board WHERE title = #{title}")
    public Board findByTitle(String Title);
	
	//한페이지당 게시글수
	@Select("SELECT * FROM (SELECT rownum as rnum, b.* FROM (SELECT /*+ index_desc(board board_pk_bno)*/ * FROM board) b WHERE rownum <= #{endRownum}) WHERE rnum >= #{startRownum}")
    public List<Board> findAll(Long startRownum, Long endRownum);		//page

    @Select("select count(*) from product")
	public Long count();
    
}
