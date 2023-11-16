package com.babasnack.demo.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.babasnack.demo.board.dto.BoardDto;
import com.babasnack.demo.entity.Board;

@Mapper
public interface BoardDao {
	//글 저장
	@Insert("INSERT INTO board (bno, title, board_notice, board_writer, board_date, board_state, board_code) " +
	        "VALUES (#{bno}, #{title}, #{boardNotice, jdbcType=VARCHAR}, #{boardWriter}, sysdate, #{boardState}, #{boardCode})")
	@SelectKey(statement = "SELECT board_seq.nextval FROM dual", before = true, keyProperty = "bno",  resultType = Long.class)
	public Long addBoard(Board board);
	
	//글 수정
 	@Update("UPDATE board SET title = #{title}, " +
 	        "board_notice = #{boardNotice}, " +
 	        "board_state = #{boardState}, " +
 	        "board_code = #{boardCode} WHERE bno = #{bno}")
 	public Long updateBoard(Board board);
	
	//글 삭제
    @Delete("DELETE FROM board WHERE bno = #{bno}")
    public Integer deleteBoard(Long bno);
	
	//글 목록
	@Select("SELECT * FROM board")
    public List<Board> FindAllByBoard();
	
	//공지글이면 위로 정렬하는 글목록
	@Select("SELECT bno, title, board_notice AS boardNotice, board_writer AS boardWriter, board_date AS boardDate, CASE WHEN notice = 1 THEN true ELSE false END AS boardState, board_code AS boardCode FROM board ORDER BY CASE WHEN notice = 1 THEN 0 ELSE 1 END ASC, created_date DESC")
    List<Board> isSortedByBoard();
	
	//제목으로 조회
    @Select("SELECT * FROM board WHERE title = #{title}")
    public Board findByTitle(String Title);
	
	//한페이지당 게시글수
	@Select("SELECT * FROM (SELECT rownum as rnum, b.* FROM (SELECT /*+ index_desc(board board_pk_bno)*/ * FROM board) b WHERE rownum <= #{endRownum}) WHERE rnum >= #{startRownum}")
    public List<Board> boardPageOne(Long startRownum, Long endRownum);		//page

	@Select("SELECT count(*) FROM board")
	public Long boardAllCount();
    
    // 게시글 비밀번호 설정
	@Update("UPDATE board SET board_code = #{boardCode} WHERE bno = #{bno}")
    public Long setBoardCode(BoardDto.WriteB writeB);

    // 설정된 비밀번호 확인
    @Select("SELECT COUNT(*) FROM board WHERE bno = #{bno} AND board_code = #{boardCode}")
    public boolean checkBoardCode(Long bno, int boardCode);
    
}
