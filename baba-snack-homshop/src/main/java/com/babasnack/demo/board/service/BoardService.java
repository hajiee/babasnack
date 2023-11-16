package com.babasnack.demo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.babasnack.demo.board.dao.BoardDao;
import com.babasnack.demo.board.dto.BoardDto;
import com.babasnack.demo.board.dto.BoardPage;
import com.babasnack.demo.entity.Board;

@Service
public class BoardService {
	@Autowired
    private BoardDao boardDao;
	
	@Value("${numberOfBoardesPerPage}")
	private int numberOfBoardesPerPage;
	@Value("${sizeOfBoardPagination}")
	private int sizeOfBoardPagination;

    // 글 저장
	public Long addBoard(BoardDto.WriteB writeB) {
        Board board = new Board();
        board.setTitle(writeB.getTitle());
        board.setBoardNotice(writeB.getBoardNotice());
        board.setBoardState(writeB.isBoardState());
        board.setBoardCode(writeB.getBoardCode());
        return boardDao.addBoard(board);
    }

    // 글 수정
	public Long updateBoard(Board upBoardWrite) {
        return boardDao.updateBoard(upBoardWrite);
    }

    // 글 삭제
    public Integer deleteBoard(Long bno) {
        return boardDao.deleteBoard(bno);
   }

   // 글 목록 조회 (페이징)
   public List<Board> getBoards(Long startRownum, Long endRownum) {
       return boardDao.boardPageOne(startRownum, endRownum);
   }

   // 공지글이면 위로 정렬하는 글 목록 조회
   public List<Board> getIsSortedBoards() {
       return boardDao.isSortedByBoard();
   }

   // 제목으로 게시글 조회
   public Board findbyTitle(String title) {
       return boardDao.findByTitle(title);
   }
   
	// 게시글 비밀번호 설정
	public Long setboardCode(BoardDto.WriteB writeB) {
	    return boardDao.setBoardCode(writeB);
	}

	// 설정된 비밀번호 확인
	public boolean checkBoardCode(Long bno, int code) {
	    return boardDao.checkBoardCode(bno, code);
	}
	
	// 게시판 page
    public BoardPage boardPage(Long pageno) {
        Long count = boardDao.boardAllCount();
        Long numberOfPage = (count - 1) / numberOfBoardesPerPage + 1;

        // 현재 페이지의 시작과 끝 인덱스 계산
        Long startRownum = (pageno - 1) * numberOfBoardesPerPage + 1;
        Long endRownum = pageno * numberOfBoardesPerPage;

        List<Board> boards = boardDao.boardPageOne(startRownum, endRownum);

        // 페이지네이션 계산
        Long start = (pageno - 1) / sizeOfBoardPagination * sizeOfBoardPagination + 1;
        Long prev = start - 1;
        Long end = prev + sizeOfBoardPagination;
        Long next = end + 1;

        if (end >= numberOfPage) {
            end = numberOfPage;
            next = 0L;
        }

        // BoardPage 객체를 생성하고 게시글 목록을 포함하여 반환
        return new BoardPage(prev, start, end, next, pageno, boards);
    }


}
