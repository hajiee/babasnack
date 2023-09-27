package com.babasnack.demo.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@Controller
public interface ErrorController {

    @RequestMapping("/error")
    public default String handleError(Model model) {
        // 에러 메시지 설정
        model.addAttribute("errorMsg", "An error occurred."); // 실제 에러 메시지로 변경 가능

        return "error"; // 에러 페이지의 뷰 이름을 반환합니다.
    }
    
    @ExceptionHandler(Exception.class)
    public default String handleException(Exception ex, Model model) {
        // 예외 처리 로직 및 모델 설정
    	System.out.println(ex.getMessage());
        return "error"; // 에러 페이지의 뷰 이름 반환
    }
}