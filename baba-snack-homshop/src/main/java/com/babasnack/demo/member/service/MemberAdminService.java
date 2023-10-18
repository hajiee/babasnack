package com.babasnack.demo.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;

@Service
public class MemberAdminService {
	@Autowired
	private MemberDao memberDao;
	
	// 모든 상품 조회
		public List<Member> getAllMember() {
			return memberDao.findAllForMember();
		}
}
