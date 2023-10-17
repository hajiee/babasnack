package com.babasnack.demo.member.controller;

import java.lang.reflect.Member;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.member.service.MemberService;

@Controller
public class MemberController {
    @Autowired
    private MemberService service;

    // 접근제어
    @PreAuthorize("permitAll()")
    @GetMapping("/member/join")
    public ModelAndView psJoin() {
        return new ModelAndView("member/join");
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/member/login")
    public ModelAndView psLogin() {
    	return new ModelAndView("member/login");
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/member/login")
    public ModelAndView psLogin(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        // 로그인 처리 로직 구현
        if (authenticate(username, password)) {
            // 인증 성공 시
            return new ModelAndView("redirect:/main");  // 메인 페이지로 리다이렉트
        } else {
            // 인증 실패 시
            ModelAndView modelAndView = new ModelAndView("member/login");
            modelAndView.addObject("error", "Invalid username or password");  // 에러 메시지 추가
            return modelAndView;
        }
    }

    private boolean authenticate(String username, String password) {
        // 실제 인증 처리를 수행하는 로직을 구현해야 합니다.
        // 예를 들어, 데이터베이스에서 사용자 정보를 조회하고 비밀번호 일치 여부 등을 확인할 수 있습니다.
        
        // 임시로 예시적으로 'admin'이라는 사용자명과 'password'라는 비밀번호로만 인증되도록 설정합니다.
        return "admin".equals(username) && "password".equals(password);
    }


	// 회원가입
	@PostMapping("/member/join")
	public ModelAndView psJoin(MemberDto.Join dto) {
	    service.join(dto);
	    return new ModelAndView("redirect:/main");
	}

	@GetMapping("/member/findbypw")
	public void checkPw() {

	}

	// 비밀번호 확인
	@PostMapping("/member/findbypw")
	public ModelAndView checkPw(String password, Principal principal, HttpSession session, RedirectAttributes ra) {
	    Boolean result = service.checkPw(password, principal.getName());

	    // 비밀번호가 틀린 경우 에러 메시지를 RedirectAttributes에 저장
	    if (!result) {
	        ra.addFlashAttribute("msg", "비밀번호를 확인하지 못했습니다");
	        return new ModelAndView("redirect:/main");
	    }

	    // 비밀번호가 맞은 경우 세션에 비밀번호를 확인했다고 저장
	    session.setAttribute("CheckPw", true);
	    return new ModelAndView("redirect:/main");
	}

	// 내 정보 보기
	@GetMapping("/member/user-profile")
	public ModelAndView psMyPage(Principal principal, HttpSession session) {
	    // 비밀번호 확인이 안된 경우 "/member/findbypw"로 이동
        if (session.getAttribute("CheckPw") == null)
            return new ModelAndView("redirect:/main");

        MemberDto.PsMyPage dto = service.psMypage(principal.getName());
        return new ModelAndView("member/user-profile").addObject("dto", dto);
	}

   // 이메일 변경
   @PostMapping("/member/user-profile")
   public ModelAndView psChangeEm(String email, Principal principal) {
       service.psChangeEm(email, principal.getName());
       return new ModelAndView("redirect:/member/user-profile");
   }

   
   @GetMapping("/member/{username}/user-profile")
   public ModelAndView psWithdrawal(@PathVariable("username") String username) {
       // GET 요청을 처리하는 로직 구현
       // 예를 들어, 회원 정보 조회 등의 작업을 수행할 수 있습니다.
       
       
       Member member = (Member) service.getMemberByUsername(username);
       
       ModelAndView modelAndView = new ModelAndView("withdrawal");
       modelAndView.addObject("member", member);
       
       return new ModelAndView("member/user-profile");
   }

   
   // 회원 탈퇴 + 로그아웃 
   @PostMapping("/member/{username}")
   public ModelAndView psWithdrawal(Principal principal, HttpSession session) {
       session.invalidate();
       service.psWithdrawal(principal.getName());
       return new ModelAndView("redirect:/");
   }
}
