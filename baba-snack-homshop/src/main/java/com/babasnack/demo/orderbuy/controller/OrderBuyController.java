package com.babasnack.demo.orderbuy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Member;
import com.babasnack.demo.orderbuy.dto.OrderBuyDto;
import com.babasnack.demo.orderbuy.service.OrderBuyService;

@Secured("ROLE_USER")
@Controller
public class OrderBuyController {
	@Autowired
	private OrderBuyService orderBuyService;

	// 주문정보 저장
	@PostMapping("/orderbuy/{username}")
	public ModelAndView orderBuyInsert(OrderBuyDto.OrderBuyProduct orderBuyProduct,
			@PathVariable("username") String username) {
		orderBuyService.add(orderBuyProduct, username);
		return new ModelAndView("redirect:/order-list");
	}

	// 장바구니에서 전체주문(로그인이 되어있는 회원의 id와 주문하는 id가 같아야 함) - 작성중
//	@PostMapping("/cart/orderbuy/{username}")
//	public ModelAndView orderBuyCart(@PathVariable("username") String username) {
//
//		return new ModelAndView("redirect:/orderdetails");
//	}
	
	
	// 주문 후 상품 수량 감소 - 작성중
	@PostMapping("/member-list")
	public ModelAndView decreaseProduct(Long productStock, Long pno) {
		
		return new ModelAndView("redirect:/cart/read");
	}

	// 주문상품 발송여부(관리자)
	@PostMapping("/member-list")
	public ModelAndView deliveryUpdate(String deliveryState, Principal principal, Long ono) {
		deliveryState = "상품발송 완료";
		orderBuyService.deliveryUpdate(deliveryState, principal.getName(), ono);
		return new ModelAndView("redirect:/member-list");
	}

}
