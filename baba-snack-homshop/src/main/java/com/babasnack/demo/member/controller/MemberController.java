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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Member;
import com.babasnack.demo.member.dto.MemberDto;
import com.babasnack.demo.member.service.MemberResetService;
import com.babasnack.demo.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService service;
    @Autowired
    private MemberResetService memberResetService;
    
    @PreAuthorize("permitAll()")
    @GetMapping("/join")
    public ModelAndView psJoin() {
        return new ModelAndView("member/join");
    }
    
    @PostMapping("/join")
    public ModelAndView join(@ModelAttribute("dto") MemberDto.Join dto) {
        boolean joinResult = service.join(dto);

        if (joinResult) {
            // 회원가입 성공 시 로그인 페이지로 이동
            return new ModelAndView("redirect:/member/login");
        } else {
            // 회원가입 실패 시 회원가입 페이지로 이동하며 실패 메시지 전달
            ModelAndView modelAndView = new ModelAndView("member/join");
            modelAndView.addObject("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
            return modelAndView;
        }
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public ModelAndView psLogin() {
        return new ModelAndView("member/login");
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
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

    @GetMapping("/logout")
    public ModelAndView psLogout(HttpSession session) {
        // 세션에서 로그인 정보 제거
        session.removeAttribute("isLogged");
        session.removeAttribute("username");

        // 스프링 시큐리티의 로그아웃
        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/main");
    }

    @GetMapping("/user-profile")
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    public ModelAndView psMyProfile(Principal principal, HttpSession session) {
        // 사용자 정보 조회 등의 로직 수행
        String username = principal.getName(); // Principal에서 사용자명 가져오기
        MemberDto.PsMyPage dto = psMypage(username);

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

    @PostMapping("/change-email")
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


    @PostMapping("/change-password")
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

    @GetMapping("/withdrawal")
    public ModelAndView withdrawalPage(HttpSession session) {
        // 로그인 상태 확인
        if (session.getAttribute("isLogged") == null) {
            return new ModelAndView("redirect:/member/login");
        }

        ModelAndView modelAndView = new ModelAndView("member/user-profile");
        modelAndView.addObject("withdrawalForm", true); // 회원 탈퇴 폼을 보여주기 위한 플래그 추가

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/withdrawal")
    public ModelAndView psWithdrawal(Principal principal, HttpSession session) {
        // 회원 탈퇴 처리 로직 수행
        String username = principal.getName();
        Boolean withdrawalResult = service.psWithdrawal(username);
        System.out.println("2222222222222222222222222222");
        if (withdrawalResult) {
            System.out.println("회원 탈퇴가 성공적으로 처리되었습니다.");
        } else {
            System.out.println("회원 탈퇴 처리가 실패하였습니다.");
        }
        System.out.println("33333333333333333333333333333333333333333333");
        
        session.invalidate();
        System.out.println("444444444444444444444444444444444444444");
        // 스프링 시큐리티의 로그아웃

        // 회원 탈퇴 후 리다이렉트할 경로 설정
        return new ModelAndView("redirect:/main");
    }


    @GetMapping("/findbyid")
    public ModelAndView psFindById(@RequestParam(value = "email", required = false) String email) {
        ModelAndView modelAndView = new ModelAndView();

        if (email == null) {
            modelAndView.setViewName("findbyid"); // email 파라미터가 없는 경우 findbyid.jsp를 반환
        } else {
            // 이메일을 통해 사용자 아이디 조회 로직 수행
            String username = service.findUsernameByEmail(email);

            if (username != null) {
                modelAndView.setViewName("findbyid-result");
                modelAndView.addObject("username", username);
            } else {
                modelAndView.setViewName("findbyid-failure");
            }
        }

        return modelAndView;
    }

    @GetMapping("/findbypw")
    public ModelAndView psFindByPw() {
        return new ModelAndView("findbypw");
    }

    @PostMapping("/password-reset")
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
