package com.babasnack.demo.memberdto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MemberDto {
	
	@Data
	@AllArgsConstructor
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
