package com.babasnack.demo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.board.dao.BoardAdminDao;
import com.babasnack.demo.entity.Board;

@Service
public class BoardAdminService {
	@Autowired
    private BoardAdminDao boardAdminDao;
	
	// 번호로 게시글 조회
    public Board findByBnoForAdmin(Long bno){
       	return boardAdminDao.findByBoard(bno);

     }
     
     // 관리자용 모든 게시글 열람 
     public List<Board> getAllBoardsForAdmin(){
        return boardAdminDao.findAllForAdmin();
     }
}
