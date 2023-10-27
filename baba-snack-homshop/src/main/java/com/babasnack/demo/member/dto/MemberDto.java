package com.babasnack.demo.member.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class MemberDto {
    @Getter
    @Data
    public static class Join {
        private String username;
        private String password;
        private String email;
        private String joinDay = LocalDate.now().toString();
        private Long pnoTell;

        // Getter and Setter for pnoTell

        public Member toEntity(String encodedPassword) {
            return new Member(username, encodedPassword, email, LocalDate.parse(joinDay), pnoTell);
        }
    }

    @Data
    @AllArgsConstructor
    public static class PsMyPage {
        private String username;
        private String email;
        private String joinDayFormatted;
        private Long daysSinceJoining;
    }

    @Getter
    public static class Member {
        private String username;
        private String password;
        private String email;
        private LocalDate joinDay;
        private Long pnoTell;

        public Member(String username, String password, String email,
                      LocalDate joinDay, Long pnoTell) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.joinDay = joinDay;
            this.pnoTell = pnoTell;
        }

        // Getter and Setter methods for the fields

    }
}
