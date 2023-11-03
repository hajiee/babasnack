package com.babasnack.demo.orderdetail.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.entity.OrderDetail;
import com.babasnack.demo.orderdetail.dto.OrderDetailDto;
import com.babasnack.demo.orderdetail.dto.OrderDetailDto.ReadOrderDetailAdmin;
import com.babasnack.demo.orderdetail.service.OrderDetailService;

//@Secured("ROLE_USER")
@Controller
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;

	// 주문상세 페이지 정보 출력 테스트
//	@GetMapping("/cart/orderdetails")
//	public void orderDetailMemberTest() {
//	}

	// 회원 주문자 정보
//	@GetMapping("/cart/orderdetails/{username}")
//	public ModelAndView orderDetailMember(Principal principal, Long ono) {
//		OrderDetailDto.ReadOrderDetailMember dto = orderDetailService.orderDetailMember(principal.getName(), ono);
//		return new ModelAndView("redirect:/cart/orderdetails").addObject("dto", dto);
//	}

	// 주문상세 페이지 정보 출력
	@GetMapping("/cart/orderdetails")
	public ModelAndView orderDetailProfile(Principal principal) {
		// ODMemberDto : 회원 주문자 정보(member 테이블에서)
		OrderDetailDto.ReadMemberProfile ODMemberDto = orderDetailService.readMemberProfile(principal.getName());
		return new ModelAndView("cart/orderdetails").addObject("ODMemberDto", ODMemberDto);
	}

	// 주문상세 정보 저장
	@PostMapping("/cart/orderdetails/{username}")
	public ModelAndView addOrderDetail(OrderDetail orderDetail, @PathVariable("username") String username) {
		orderDetailService.add(orderDetail, username);
		return new ModelAndView("redirect:/orderdetails");
	}

	// 회원 주문자 정보(관리자)
	@GetMapping("/admin-member")
	public ModelAndView orderDetailAdmin(Principal principal, Long ono) {
		OrderDetailDto.ReadOrderDetailMember dto = orderDetailService.orderDetailMember(principal.getName(), ono);
		return new ModelAndView("redirect:/admin-member").addObject("dto", dto);
	}

	// 회원 주문목록(관리자)
	@GetMapping("/admin-member/member-list")
	public ModelAndView orderDetailAdminProduct(Principal principal, Long ono) {
		List<ReadOrderDetailAdmin> list = orderDetailService.orderDetailAdmin(principal.getName(), ono);
		return new ModelAndView("redirect:/admin-member").addObject("list", list);
	}
}
