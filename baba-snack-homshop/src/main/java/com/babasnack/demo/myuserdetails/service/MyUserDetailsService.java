package com.babasnack.demo.myuserdetails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dao.MemberDao;

@Component("myUserDetailsServiceNew")
public class MyUserDetailsService implements UserDetailsService {
    
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailsService(MemberDao memberDao, PasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
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
