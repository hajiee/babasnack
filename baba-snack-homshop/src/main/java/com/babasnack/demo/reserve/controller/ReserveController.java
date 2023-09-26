package com.babasnack.demo.reserve.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babasnack.demo.entity.Reserve;
import com.babasnack.demo.reserve.service.ReserveService;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    private final ReserveService reserveService;

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping("/{username}")
    public String getReserveByUsername(@PathVariable String username, Model model) {
        Reserve reserve = reserveService.getReserveByUsername(username);
        model.addAttribute("reserve", reserve);
        return "reserve";
    }

    @PostMapping("/")
    public String createReserve(@ModelAttribute Reserve reserve, Model model) {
        Reserve createdReserve = reserveService.createReserve(reserve);
        model.addAttribute("result", createdReserve);
        return "result";
    }

  	@PutMapping("/{username}")
  	public String updateReserveByUsername(@PathVariable String username, @ModelAttribute Reserve reserve, Model model) {
      	Reserve updatedReserve = reserveService.updateReserveByUsername(username, reserve);
      	model.addAttribute("result", updatedReserve);
      	return "result";
  	}
}