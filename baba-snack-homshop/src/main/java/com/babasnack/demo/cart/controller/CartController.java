package com.babasnack.demo.cart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.cart.service.CartService;

//@Secured("ROLE_USER")
@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	// 장바구니 출력 테스트
	@GetMapping("/cart/orderdetails-list")
	public void readCartTest() {
	}

	// 로그인한 회원의 장바구니를 출력
	@GetMapping("/cart/orderdetails-list/{username}")
	public ModelAndView read(Principal principal) {
		CartDto.ReadCart cartDto = cartService.read(principal.getName());
		return new ModelAndView("cart/orderdetails-list").addObject("cartDto", cartDto);
	}

	// 로그인 아이디와 상품 번호로 장바구니에 상품 추가
	@PostMapping("/cart/orderdetails-list/add")
	public ModelAndView add(Long pno, Principal principal) {
		cartService.add(pno, principal.getName());
		return new ModelAndView("redirect:/cart/orderdetails-list");
	}

	// 장바구니 상품 전체삭제
	@PostMapping("/cart/orderdetails-list/delete")
	public ModelAndView delete(Principal principal) {
		cartService.deleteAll(principal.getName());
		return new ModelAndView("redirect:/cart/orderdetails-list");
	}
}
