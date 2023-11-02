package com.babasnack.demo.member.controller;

import java.security.Principal;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.member.service.MemberResetService;
import com.babasnack.demo.member.service.MemberService;

@Controller
public class MemberController {
    @Autowired
    private MemberService service;
    @Autowired
    private MemberResetService memberResetService;

    @PreAuthorize("isAnonymous()")
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
                                @RequestParam("password") String password,
                                HttpSession session) {
        // 로그인 처리 로직 구현
        if (authenticate(username, password)) {
            // 인증 성공 시 세션에 로그인 정보 저장
            session.setAttribute("isLogged", true);
            session.setAttribute("username", username);
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

    @GetMapping("/member/logout")
    public ModelAndView psLogout(HttpSession session) {
        // 세션에서 로그인 정보 제거
        session.removeAttribute("isLogged");
        session.removeAttribute("username");

        // 스프링 시큐리티의 로그아웃
        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/main");
    }

    @GetMapping("/member/user-profile")
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    public ModelAndView psMyPage(Principal principal, HttpSession session) {
        // 사용자 정보 조회 등의 로직 수행
        String username = principal.getName(); // Principal에서 사용자명 가져오기
        MemberDto.PsMyPage dto = service.psMypage(username);

        ModelAndView modelAndView = new ModelAndView("member/user-profile");
        modelAndView.addObject("dto", dto);

        // 네비게이션 메뉴 표시를 위한 로직 추가
        modelAndView.addObject("isLogged", true);

        return modelAndView;
    }
    
    public MemberDto.PsMyPage psMypage(String username) {
        Member m = getMemberByUsername(username);
        if (m == null) {
            // m이 null인 경우에 대한 처리를 추가합니다.
            // 예를 들어, null을 반환하거나 기본 값을 설정할 수 있습니다.
            return null; // 혹은 적절한 기본 값을 반환
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        String joinDayFormatted = dtf.format(m.getJoinDay());

        Long daysSinceJoining = ChronoUnit.DAYS.between(m.getJoinDay(), LocalDate.now());

        return new MemberDto.PsMyPage(m.getUsername(), m.getEmail(), joinDayFormatted, daysSinceJoining);
    }

    private Member getMemberByUsername(String username) {
        return service.getMemberByUsername(username);
    }



	@PostMapping("/member/change-email")
    public ModelAndView psChangeEmail(@RequestParam("email") String newEmail,
                                      HttpSession session) {
        // 로그인 상태 확인
        if (session.getAttribute("isLogged") == null) {
            return new ModelAndView("redirect:/member/login");
        }

        // 이메일 변경 로직 수행
        String username = (String) session.getAttribute("username");
        service.psChangeEm(username, newEmail);

        // 변경된 이메일로 세션 정보 업데이트
        session.setAttribute("email", newEmail);

        return new ModelAndView("redirect:/member/user-profile");
    }


    @PostMapping("/member/change-password")
    public ModelAndView psChangePassword(@RequestParam("currentPassword") String currentPassword,
                                         @RequestParam("newPassword") String newPassword,
                                         HttpSession session) {
        // 로그인 상태 확인
        if (session.getAttribute("isLogged") == null) {
            return new ModelAndView("redirect:/member/login");
        }

        // 비밀번호 변경 로직 수행
        String username = (String) session.getAttribute("username");
        service.psChangePassword(username, currentPassword, newPassword);

        return new ModelAndView("redirect:/member/user-profile");
    }


    @PostMapping("/member/withdrawal")
    public ModelAndView psWithdrawal(HttpSession session) {
        // 로그인 상태 확인
        if (session.getAttribute("isLogged") == null) {
            return new ModelAndView("redirect:/member/login");
        }

        // 회원 탈퇴 처리 로직 수행
        String username = (String) session.getAttribute("username");
        service.psWithdrawal(username);

        // 세션에서 로그인 정보 제거
        session.removeAttribute("isLogged");
        session.removeAttribute("username");

        // 스프링 시큐리티의 로그아웃
        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/main");
    }
    @GetMapping("/member/findbypw")
    public ModelAndView psFindByPw() {
        return new ModelAndView("findbypw");
    }
    
    @PostMapping("/member/findbypw")
    public ModelAndView psPasswordReset(@RequestParam("email") String email,
                                        @RequestParam("username") String username) {
        
    	if (email != null && username != null) {
            memberResetService.resetPassword(email, username);
            return new ModelAndView("password-reset-success");
        } else {
            return new ModelAndView("password-reset-failure");
        }
    }
}