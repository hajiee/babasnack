package com.babasnack.demo.delivery.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.delivery.dto.DeliveryDto;
import com.babasnack.demo.delivery.service.DeliveryService;
import com.babasnack.demo.entity.Delivery;

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
	@PostMapping("/delivery/add/{username}")
	public ModelAndView add(DeliveryDto.DeliveryEntity deliveryEntity, @PathVariable("username") String username) {
		// @PathVariable("username") String username (회원 아이디 테스트용)
		// Principal principal

		System.out.println("=================");
		deliveryService.add(deliveryEntity);
		System.out.println(deliveryEntity);
		System.out.println("=================");
		return new ModelAndView("redirect:/");
	}

	// 배송지 회원 아이디 중복확인용
	@ResponseBody
	@RequestMapping(value = "/delivery/usernameCheck", method = RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) {
		String username = req.getParameter("username");
		Delivery deliveryIdCheck = deliveryService.findByUsername(username);
		
		int result = 0;
		
		if(deliveryIdCheck != null){
			  result = 1;
		 }
		
		return result;		
	}

	// 배송지 수정 후 마이 페이지로
	@PostMapping("/delivery/change/{username}")
	public ModelAndView change(DeliveryDto.DeliveryEntity deliveryEntity, @PathVariable("username") String username) {
		// @PathVariable("username") String username
		// Principal principal
		System.out.println("=================");
		deliveryService.change(deliveryEntity);
		System.out.println(deliveryEntity);
		System.out.println("=================");
		return new ModelAndView("redirect:/");
		// return new ModelAndView("redirect:/mypage");
	}
}
