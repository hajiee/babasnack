package com.babasnack.demo.member.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;



@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password(encoder.encode("1234"))
                    .roles("ADMIN")
                    .build();
        }

        Member member = memberDao.findById(username);

        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.builder()
                .username(username)
                .password(member.getPassword())
                .roles(member.getRole() != null ? member.getRole() : "")
                .build();
    }

	}
