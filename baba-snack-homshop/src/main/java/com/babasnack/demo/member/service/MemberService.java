package com.babasnack.demo.member.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            Member member = Member.builder()
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                    .password(encodedPassword)
                    .build();
            
            LocalDate joinDate = LocalDate.parse(dto.getJoinDay());
            member.setJoinDay(joinDate);

            Integer savedId = memberDao.save(member);

            return savedId != null;
        } else {
            return false;
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
	 @Transactional
	    public boolean psChangePassword(String username, String currentPassword, String newPassword) {
	        Member member = memberDao.findById(username);
	        if (member == null) {
	            return false; // 사용자가 존재하지 않을 경우 변경 실패로 처리
	        }

	        // 현재 비밀번호 확인
	        boolean isCurrentPasswordCorrect = passwordEncoder.matches(currentPassword, member.getPassword());
	        if (!isCurrentPasswordCorrect) {
	            return false; // 현재 비밀번호가 일치하지 않을 경우 변경 실패로 처리
	        }

	        // 새로운 비밀번호 설정
	        String encodedNewPassword = passwordEncoder.encode(newPassword);
	        member.setPassword(encodedNewPassword);

	        return true; // 비밀번호 변경 성공
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

	@Transactional(readOnly = true)
    public Member getMemberByUsername(String username) {
        return memberDao.findById(username);
    }
}
