package com.babasnack.demo.member.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.babasnack.demo.member.service.MemberResetService;
import com.babasnack.demo.member.service.MemberService;

@RestController
public class MemberRestController {
    private final MemberService memberService;
    private final MemberResetService memberResetService;

    @Autowired
    public MemberRestController(MemberService memberService, MemberResetService memberResetService) {
        this.memberService = memberService;
        this.memberResetService = memberResetService;
    }

    // 아이디 사용여부 체크
    @GetMapping("/member/username/available/{username}")
    public ResponseEntity<Void> checkUsernameAvailable(@PathVariable String username) {
        boolean isAvailable = memberService.usernameAvailable(username);
        if (isAvailable) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

   
    
}
