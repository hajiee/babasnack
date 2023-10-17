package com.babasnack.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private String username;
	private String password;
	@Builder.Default
	private Long pnoTell=0L;
	private String email;
	private LocalDateTime joinDay;
	private String role;
	public String getRole() {
        // 회원의 역할(role) 정보를 반환합니다.
        // 예: "ADMIN", "USER"
        return role;
    }
	
	
}
