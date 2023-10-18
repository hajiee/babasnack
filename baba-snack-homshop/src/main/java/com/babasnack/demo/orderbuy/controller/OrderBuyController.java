package com.babasnack.demo.orderbuy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.orderbuy.dto.OrderBuyDto;
import com.babasnack.demo.orderbuy.service.OrderBuyService;

//@Secured("ROLE_USER")
@Controller
public class OrderBuyController {
	@Autowired
	private OrderBuyService orderBuyService;
	
	@GetMapping("/cart/pay")
	public void readOrderBuyTest() {
	}

	// 주문정보 저장(회원 id)
	@PostMapping("/cart/orderdetails-list/{username}")
	public ModelAndView orderBuyInsert(OrderBuyDto.OrderBuyProduct orderBuyProduct,
			@PathVariable("username") String username) {
		orderBuyService.add(orderBuyProduct, username);
		return new ModelAndView("redirect:/cart/orderdetails/{username}");
	}

	// 장바구니에서 전체주문(결제) - 작성중
	@PostMapping("/cart/pay/{username}")
	public ModelAndView orderBuyCart(Long reservePlus, Long allReserve, Long odno, Long pno, Long ono,
			@PathVariable("username") String username) {
		// 회원 id + 주문번호가 null이 아닐 때
		if (orderBuyService.findByUsernameAndOno(username, ono) != null) {

			// 최종 결제 금액

			// 적립금 누적,감소 작성중
			orderBuyService.orderBuyCartInsert(reservePlus, allReserve, ono, username);

			// 주문후 상품 재고 감소
			List<OrderBuyDto.ReadOrderDetailByOB> orderDetailProductsList = orderBuyService.findOrderDetailByOdno(odno);
			OrderBuyDto.ProductOrderBuyDto productOrderBuyDto = new OrderBuyDto.ProductOrderBuyDto();

			for (OrderBuyDto.ReadOrderDetailByOB i : orderDetailProductsList) {
				productOrderBuyDto.setPno(i.getPno());
				productOrderBuyDto.setProductCnt(i.getBuyCnt());
				orderBuyService.decreaseProduct(productOrderBuyDto, pno);
			}

			// 주문후 특정회원의 장바구니 상품 전체 삭제
			orderBuyService.deleteAllCartByUsername(username);

			return new ModelAndView("redirect:/order-end/{username}");
		} else {
			// 회원 id + 주문번호가 null이었을 때 메인페이지로 이동
			return new ModelAndView("redirect:/");
		}
	}

	// 모든 상품들 10% 적립금 저장(update문)
	@GetMapping("/product/reserve")
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
