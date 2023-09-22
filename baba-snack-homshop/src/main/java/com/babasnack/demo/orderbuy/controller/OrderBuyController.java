package com.babasnack.demo.orderbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.babasnack.demo.entity.OrderBuy;
import com.babasnack.demo.orderbuy.service.OrderBuyService;

@Secured("ROLE_USER")
@Controller
public class OrderBuyController {
	@Autowired
	private OrderBuyService orderBuyService;
}
