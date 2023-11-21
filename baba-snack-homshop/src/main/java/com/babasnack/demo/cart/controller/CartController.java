package com.babasnack.demo.cart.controller;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.babasnack.demo.cart.dto.CartDto;
import com.babasnack.demo.cart.service.CartService;
import com.babasnack.demo.entity.Cart;
import com.babasnack.demo.entity.Delivery;

//@Secured("ROLE_USER")
@Controller
public class CartController {
	@Autowired
	private CartService cartService;

	// 로그인한 회원의 장바구니 출력 = 완료
	@GetMapping("/cart/orderdetails-list")
	public ModelAndView read(Principal principal, @RequestParam(value="pno", defaultValue = "7") Long pno) {
		CartDto.ReadCart cartDto = cartService.read(principal.getName());
		
				
		return new ModelAndView("cart/orderdetails-list").addObject("cartDto", cartDto);
	}

	// 장바구니 상품이미지 보여주기 위한 설정 = 완료
	@GetMapping("/productImg/{productSaveImg}")
	public ResponseEntity<byte[]> viewProductSaveImg(@PathVariable String productSaveImg) {
		File file = new File("c:/upload/productImg", productSaveImg);
		try {
			byte[] bytes = Files.readAllBytes(file.toPath());
			String contentType = Files.probeContentType(file.toPath());
			MediaType type = MediaType.parseMediaType(contentType);
			return ResponseEntity.ok().contentType(type).body(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 특정 회원의 장바구니 비어있는지, 아닌지 확인용 = 완료
	@ResponseBody
	@RequestMapping(value = "/cart/usernameCheck", method = RequestMethod.POST)
	public int cartIdCheck(Principal principal) {
		Cart cartIdCheck = cartService.findByUsernameFromCart(principal.getName());

		int result = 0;

		if (cartIdCheck != null) {
			result = 1;
		}

		return result;
	}

	// 특정 회원의 장바구니 비어있는지, 아닌지 확인용 = 완료
	@ResponseBody
	@RequestMapping(value = "/cart/usernameDeliveryCheck", method = RequestMethod.POST)
	public int deliveryIdCheck(Principal principal) {
		Delivery deliveryIdCheck = cartService.findByUsernameFromDelivery(principal.getName());

		int result = 0;

		if (deliveryIdCheck != null) {
			result = 1;
		}

		return result;
	}

	// 로그인 아이디와 상품 번호로 장바구니에 상품 버튼
	@PostMapping("/cart/orderdetails-list/add")
	public ModelAndView add(@RequestParam("productCnt") Long productCnt, Long pno, Principal principal) {
		cartService.add(productCnt, pno, principal.getName());
		return new ModelAndView("redirect:/cart/orderdetails-list");
	}

	// 장바구니 상품 전체삭제
	@PostMapping("/cart/orderdetails-list/delete")
	public ModelAndView delete(Principal principal) {
		cartService.deleteAll(principal.getName());
		return new ModelAndView("redirect:/cart/orderdetails-list");
	}
}
