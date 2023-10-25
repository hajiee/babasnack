package com.babasnack.demo.member.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


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
            member.setEmail(dto.getEmail()); // email 값 설정 추가
            member.setPassword(encodedPassword);
            member.setJoinDay(LocalDateTime.now().withNano(0)); // 현재 날짜와 시간으로 설정하거나, 필요에 따라 다른 값을 사용할 수 있습니다.


            Integer savedId = memberDao.save(member);

            return savedId != null; // 저장된 ID가 null이 아닌 경우에만 true 반환
        } else {
            // 예외 처리 또는 다른 처리 방법을 선택하세요.
            return false; // 예를 들어, 비밀번호가 없으면 가입을 실패로 처리할 수 있습니다.
        }
    }

	  @Transactional(readOnly = true)
	    public String findById(String email) { // 파라미터 이름을 email로 변경
	        Member member = memberDao.findByEmail(email); // findByEmail로 변경
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
