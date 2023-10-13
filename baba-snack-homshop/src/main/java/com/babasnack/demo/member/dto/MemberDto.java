package com.babasnack.demo.member.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




public class MemberDto {
	@Data
	@NoArgsConstructor
	  public static class Join {
	        private String username;
	        private String password;
	        private String email;
	        private LocalDate joinDay=LocalDate.now();
	    }

    @Data
    @AllArgsConstructor
    public static class PsMyPage {
        private String username;
        private String email;
        private String joinDayFormatted;
        private Long daysSinceJoining;
    }
}
