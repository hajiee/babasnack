package com.babasnack.demo.member.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.memberservice.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	//접근제어
	@PreAuthorize("isAnonymous()")
	@GetMapping("/member/join")
	public void Psjoin() {
		
	}
	
	@PreAuthorize("isAnonymous()")
	@GetMapping("/member/login")
	public void Pslogin() {	
	}
	
	//회원가입
	@PostMapping("/member/join")
	public ModelAndView Psjoin(MemberDto.join dto) {
		service.join(dto);
		return new ModelAndView("redirect:/member/login");
	}
	@Secured("ROLE_USER")
	@GetMapping("/member/findbypw")
	public void CheckPw() {
		
	}
	
	//비밀번호 확인
	@Secured("ROLE_USER")
	@PostMapping("/member/findbypw")
	public ModelAndView CheckPw(String password,Principal principal,HttpSession session,RedirectAttributes ra) {
		Boolean result = service.CheckPw(password,principal.getName());
		
		//비밀번호가 틀린경우 에러메세지를 RedirectAttributes에 저장
		if (result==false) {
			ra.addFlashAttribute("msg","비밀번호를 확인하지 못했습니다");
			return new ModelAndView("redirect:/member/findbypw");
		}
		//비밀번호가 맞은경우 세션에 비밀번호를 확인했다고 저장
		session.setAttribute("CheckPw", true);
		return new ModelAndView("redirect:/member/user-profile");
	}
	
	//내정보 보기
	@Secured("ROLE_USER")
	@GetMapping("member/user-profile")
	public ModelAndView PsMyPage(Principal principal,HttpSession session) {
		//비밀번호 확인이 안된경우 "/member/findbypw로 이동
		if(session.getAttribute("CheckPw")==null)
			return new ModelAndView("redirect:/member/findbypw");
		MemberDto.PsMyPage dto=service.psmypage(principal.getName());
		return new ModelAndView("member/user-profile").addObject("member,dto");
	}
	
	
	//이메일 변경
	@Secured("ROLE_USER")
	@PostMapping("/member/user-profile")
	public ModelAndView PsChangeEm(String email,Principal principal) {
		service.PsChangeEm(email,principal.getName());
		return new ModelAndView("redirect:/member/user-profile");
	}
	
	//회원 탈퇴+로그아웃
	@Secured("ROLE_USER")
	@PostMapping("/member/main")
	public ModelAndView PsWithdrawl(Principal principal,HttpSession session) {
		session.invalidate();
		service.PsWithdrawl(principal.getName());
		return new ModelAndView("redirect:/");
	}
}

