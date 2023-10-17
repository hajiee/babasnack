package com.babasnack.demo.delivery.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.delivery.dto.DeliveryDto;
import com.babasnack.demo.delivery.service.DeliveryService;

//@Secured("ROLE_USER")
@Controller
public class DeliveryController {
	@Autowired
	DeliveryService deliveryService;

	// 배송지 조회 테스트
	@GetMapping("/delivery/delivery-list")
	public void readTest() {
	}

	// 배송지 조회(로그인 했을 시)
//	@GetMapping("/delivery/delivery-list/{username}")
//	public ModelAndView read(@PathVariable("username") String username) {
//		List<DeliveryDto.DeliveryEntity> deliveryList = deliveryService.search(username);
//		return new ModelAndView("delivery/delivery-list/{username}").addObject("deliveryList", deliveryList);
//	}
	

	// 배송지 저장 후 메인 페이지로
	@PostMapping("/delivery/add")
	public ModelAndView add(@PathVariable("username") String username, Long dno) {
		deliveryService.add(username, dno);
		return new ModelAndView("redirect:/");
	}

	// 배송지 수정 후 마이 페이지로
	@PostMapping("/delivery/change")
	public ModelAndView change(String name, Long pnoTell, String baseDelivery, String addDelivery,
			Principal principal) {
		deliveryService.change(name, pnoTell, baseDelivery, addDelivery, principal.getName());
		return new ModelAndView("redirect:/mypage");
	}
}
