package com.babasnack.demo.member.service;




import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;
import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.member.dto.MemberDto.PsMyPage;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private PasswordEncoder encoder;
	
	
	public void join(MemberDto.join dto) {
		
		
	}
	//아이디 체크
	@Transactional(readOnly=true)
	public Boolean usernameAvailable(String username) {
		return memberDao.FindById(username)==null;
	}
	
	//아이디 찾기
	@Transactional(readOnly=true)
	public String FindById(String email) {
		Member member = memberDao.FindByEmail(email);
		return member==null? null : member.getUsername();
	}
	
	//이메일 변경
	public boolean PsChangeEm(String email, String username) {
		return memberDao.PsChangeEm(email, username)==1;
		
	}
	
	//비밀번호 비교
	@Transactional(readOnly=true)
	public Boolean CheckPw(String password, String username) {
		
		Member member = memberDao.FindById(username);
		if(member==null)
			return false;
		return encoder.matches(password, member.getPassword());
	}

	//회원 탈퇴
	public Boolean PsWithdrawl(String username) {
		return memberDao.PsWithdrawl(username)==1;
		
	}
	//마이페이지
	public PsMyPage psmypage(String username) {
		
		Member m = memberDao.FindById(username);
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy년MM월dd일");
		String joinday=dtf.format(m.getJoinDay());
		
		Long days = ChronoUnit.DAYS.between(m.getJoinDay(), LocalDate.now());
		
		return new MemberDto.PsMyPage(m.getUsername(),m.getEmail(),joinday);
		
		
	}
	
	


	
	

	

}
