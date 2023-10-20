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

@Service
public class MemberService {
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberDao memberDao, PasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
	public Boolean usernameAvailable(String username) {
		return memberDao.findById(username) == null;
	}

	@Transactional
	public Boolean join(MemberDto.Join dto) {
	    if (dto.getPassword() != null) {
	        String encodedPassword = passwordEncoder.encode(dto.getPassword());
	        Member member = new Member();
	        member.setUsername(dto.getUsername());
	        member.setPassword(encodedPassword);

	        Long savedId = memberDao.save(member);

	        return savedId != null; // 저장된 ID가 null이 아닌 경우에만 true 반환
	    } else {
	        // 예외 처리 또는 다른 처리 방법을 선택하세요.
	        return false; // 예를 들어, 비밀번호가 없으면 가입을 실패로 처리할 수 있습니다.
	    }
	}

	@Transactional(readOnly = true)
	public String findById(String email) {
	    Member member = memberDao.findByEmail(email);
	    return member == null ? null : member.getUsername();
	}

	public boolean psChangeEm(String email, String username) {
	    return (memberDao.psChangeEm(email, username) == 1);
	}

	@Transactional(readOnly=true)
	public Boolean checkPw(String password, String username) {
	    Member member = getMemberByUsername(username);
	    if (member == null)
	        return false;
	    return passwordEncoder.matches(password, member.getPassword());
	}

	public Boolean psWithdrawal(String username) {
	    return (memberDao.psWithdrawal(username) == 1);
	}

	public MemberDto.PsMyPage psMypage(String username) {
	    Member m = getMemberByUsername(username);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
	    String joinDayFormatted = dtf.format(m.getJoinDay());

	    Long daysSinceJoining = ChronoUnit.DAYS.between(m.getJoinDay(), LocalDate.now());

	   return new MemberDto.PsMyPage(m.getUsername(), m.getEmail(), joinDayFormatted, daysSinceJoining);
	}

	private Member getMemberByUsername(String username) { 
	 	return 	memberDao.findById(username); 
   }
}
