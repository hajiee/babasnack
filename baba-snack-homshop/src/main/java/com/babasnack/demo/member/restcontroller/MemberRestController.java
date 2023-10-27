  package com.babasnack.demo.member.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.babasnack.demo.member.service.MemberService;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService service;
	
	//아이디 사용여부 체크
	@GetMapping("/member/username/available/{username}")
	public ResponseEntity<Void> usernameAvailable(@PathVariable String username){
		Boolean result = service.usernameAvailable(username);
		return result? ResponseEntity.ok(null): ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		
	}
	
	//아이디 찾기
	@GetMapping(value="/member/username/{email}")
	public ResponseEntity<String> findUsername(@PathVariable String email){
		String username=service.findById(email);
		if(username==null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디를 찾을 수 없습니다");
		return ResponseEntity.ok(username);
	}
	
}