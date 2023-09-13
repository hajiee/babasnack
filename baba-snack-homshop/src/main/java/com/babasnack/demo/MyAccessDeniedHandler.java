package com.babasnack.demo;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// X-Requested-With 헤더의 값을 가져온다
		String ajax = request.getHeader("X-Requested-With");
		Boolean isAjax = ajax!=null && ajax.equals("XMLHttpRequest");
		
		// ajax라면 403을 쏴주고 아니면 이동한다
		// (여긴 컨트롤러가 아니므로 servlet 방식으로 출력)
		if(isAjax) {
			response.sendError(403);
		} else {
			response.sendRedirect("/?error");
		}
	}
}
