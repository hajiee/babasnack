package com.babasnack.demo.delivery.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.delivery.service.DeliveryService;
import com.babasnack.demo.entity.Delivery;

@Secured("ROLE_USER")
@Controller
public class DeliveryController {
	@Autowired
	DeliveryService deliveryService;

	// 배송지 조회
	@GetMapping("/delivery")
	public ModelAndView read(Principal principal) {
		List<Delivery> list = deliveryService.search(principal.getName());
		return new ModelAndView("redirect:/delivery").addObject("list", list);
	}
		
	// 배송지 저장 후 메인 페이지로
	@PostMapping("/mypage/delivery/add")
	public ModelAndView add(Principal principal, Long dno) {
		deliveryService.add(principal.getName(), dno);
		return new ModelAndView("redirect:/");
	}
	

	// 배송지 수정 후 마이 페이지로
	@PostMapping("/delivery/change")
	public ModelAndView change(Long dno, String name, Long pnoTell, String baseDelivery, String addDelivery, Principal principal) {
		deliveryService.change(dno, name, pnoTell, baseDelivery, addDelivery, principal.getName());
		return new ModelAndView("redirect:/mypage");
	}
}
