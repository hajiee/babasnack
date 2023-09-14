package com.babasnack.demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String username;
	private String password;
	private Long pnoTell;
	private String email;
	private LocalDateTime joinDay;
}
