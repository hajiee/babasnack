package com.babasnack.demo.member.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;

@Service
public class MemberResetService {
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberResetService(MemberDao memberDao, PasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void resetPassword(String email, String username) {
        // 비밀번호 재설정 로직 구현
        String temporaryPassword = generateTemporaryPassword();

        sendEmail(email, temporaryPassword);

        updateUserPassword(username, temporaryPassword);
    }

    private String generateTemporaryPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private void sendEmail(String email, String temporaryPassword) {
        System.out.println("임시 비밀번호: " + temporaryPassword);
        // 여기에 이메일 전송 로직을 구현합니다.
    }

    private void updateUserPassword(String username, String temporaryPassword) {
        Member member = memberDao.findById(username);
        if (member != null) {
            String encodedPassword = passwordEncoder.encode(temporaryPassword);
            member.setPassword(encodedPassword);
            memberDao.update(member); // 사용자 비밀번호 업데이트
        }
    }
}
