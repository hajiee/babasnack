package com.babasnack.demo.orderbuy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.orderbuy.dto.OrderBuyDto;
import com.babasnack.demo.orderbuy.service.OrderBuyService;

@Secured("ROLE_USER")
@Controller
public class OrderBuyController {
	@Autowired
	private OrderBuyService orderBuyService;

	// 주문정보 저장(회원 id)
	@PostMapping("/orderbuy/{username}")
	public ModelAndView orderBuyInsert(OrderBuyDto.OrderBuyProduct orderBuyProduct,
			@PathVariable("username") String username) {
		orderBuyService.add(orderBuyProduct, username);
		return new ModelAndView("redirect:/order-list");
	}

	// 장바구니에서 전체주문(로그인이 되어있는 회원의 id와 주문하는 id가 같아야 함) - 작성중
	@PostMapping("/cart/orderbuy/{username}")
	public ModelAndView orderBuyCart(@PathVariable("username") String username) {
				
		// 주문 후 특정회원의 장바구니 상품 전체 삭제
		orderBuyService.deleteAllCartByUsername(username);
		
		return new ModelAndView("redirect:/orderdetails/{username}");
	}

	// 모든 상품들 10% 적립금 저장(update문)
	@PostMapping("/product/reserve")
	public ModelAndView productReserve() {
		orderBuyService.productReserve();
		return new ModelAndView("product/reserve");
	}

	// 주문상품 발송여부(관리자)
	@PostMapping("/member-list/delivery-update")
	public ModelAndView deliveryUpdate(String deliveryState, Principal principal, Long ono) {
		deliveryState = "상품발송 완료";
		orderBuyService.deliveryUpdate(deliveryState, principal.getName(), ono);
		return new ModelAndView("redirect:/member-list");
	}

}
