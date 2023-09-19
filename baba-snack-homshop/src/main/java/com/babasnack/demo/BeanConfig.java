package com.babasnack.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 비밀번호 암호화에 사용되는 BCrypt의 구현 객체를 스프링에 등록
		return new BCryptPasswordEncoder();
	}
}
