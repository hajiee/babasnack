package com.babasnack.demo.member.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;
import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.member.dto.MemberDto.Join;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private PasswordEncoder encoder;

    public Boolean join(MemberDto.Join dto) {
        String encodedPassword = encoder.encode(dto.getPassword());
        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(encodedPassword);

        return memberDao.save(dto) == 1;
    }

    // 아이디 체크
    @Transactional(readOnly=true)
    public Boolean usernameAvailable(String username) {
        return memberDao.findById(username) == null;
    }

    // 아이디 찾기
    @Transactional(readOnly=true)
    public String findById(String email) {
        Member member = memberDao.findByEmail(email);
        return (member == null) ? null : member.getUsername();
	}

	// 이메일 변경
	public boolean psChangeEm(String email, String username) {
	    return (memberDao.psChangeEm(email, username) == 1);
	}

	// 비밀번호 비교
	@Transactional(readOnly=true)
	public Boolean checkPw(String password, String username) {
	    Member member = memberDao.findById(username);
	    if (member == null)
	        return false;
	    return encoder.matches(password, member.getPassword());
	}

	// 회원 탈퇴
	public Boolean psWithdrawal(String username) {
	    return (memberDao.psWithdrawal(username) == 1);
	}

	// 마이페이지 조회
	public MemberDto.PsMyPage psMypage(String username) {
	    Member m = memberDao.findById(username);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
	    String joinDayFormatted = dtf.format(m.getJoinDay());

	    Long daysSinceJoining = ChronoUnit.DAYS.between(m.getJoinDay(), LocalDate.now());

	    return new MemberDto.PsMyPage(m.getUsername(), m.getEmail(), joinDayFormatted, daysSinceJoining);
	}
}
