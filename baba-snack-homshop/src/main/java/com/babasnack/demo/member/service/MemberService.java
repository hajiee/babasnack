package com.babasnack.demo.member.service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;
import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.member.notfoundexception.MemberNotFoundException;

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
    public String findById(String email) {
        Member member = memberDao.findByEmail(email);
        return member == null ? null : member.getUsername();
    }

    public boolean psChangeEm(String email, String username) {
        return (memberDao.psChangeEm(email, username) == 1);
    }

    @Transactional(readOnly = true)
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
            return false;
        }

        boolean isCurrentPasswordCorrect = passwordEncoder.matches(currentPassword, member.getPassword());
        if (!isCurrentPasswordCorrect) {
            return false;
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        member.setPassword(encodedNewPassword);

        return true;
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

    @Transactional(readOnly = true)
    public String findUsernameByEmail(String email) {
        return memberDao.findUsernameByEmail(email);
    }
//    @Transactional
//    public void resetPassword(String email, String username) {
//        Member member = memberDao.findByEmailAndUsername(email, username);
//        if (member == null) {
//            throw new MemberNotFoundException("해당 회원을 찾을 수 없습니다.");
//        }

//        String temporaryPassword = generateTemporaryPassword();
//        String encodedPassword = passwordEncoder.encode(temporaryPassword);
//        member.setPassword(encodedPassword);
//        memberDao.save(member);
//
//        sendTemporaryPasswordEmail(member.getEmail(), temporaryPassword);
//    }

//    private String generateTemporaryPassword() {
//        // 임시 비밀번호 생성 로직을 구현합니다.
//        // 적절한 방식으로 임시 비밀번호를 생성하여 반환합니다.
//    }

    private void sendTemporaryPasswordEmail(String email, String temporaryPassword) {
        // 이메일 관련 설정
        String host = "your_smtp_host"; // SMTP 호스트 정보
        String username = "your_email_username"; // SMTP 계정 이메일
        String password = "your_email_password"; // SMTP 계정 패스워드
        String from = "your_email_address"; // 발신자 이메일 주소
        String subject = "임시 비밀번호 발급"; // 이메일 제목
        String body = "임시 비밀번호: " + temporaryPassword; // 이메일 본문

        // SMTP 서버 설정
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

        // 인증 정보 설정
//        Authenticator auth = new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        };
//
//        // 세션 생성
//        Session session = Session.getInstance(props, auth);
//
//        try {
//            // MimeMessage 생성
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//            message.setSubject(subject);
//            message.setText(body);
//
//            // 이메일 전송
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
   }

    
}
