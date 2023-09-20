package com.babasnack.demo.member.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class MemberDto {
	@Data
	public class join {
		private String username;
		private String password;
		private String email;
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private LocalDate joinday;
		
		
	}
	
	@Data
	@AllArgsConstructor
	public static class PsMyPage{
		
		private String username;
		private String email;
		private String joinday;
		
	}
}
