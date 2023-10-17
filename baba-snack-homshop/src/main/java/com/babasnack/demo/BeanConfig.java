package com.babasnack.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                if (rawPassword == null) {
                    throw new IllegalArgumentException("rawPassword cannot be null");
                }
                return super.encode(rawPassword);
            }
        };
    }
}
