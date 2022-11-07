package com.FurnitureStore.controller;

import com.FurnitureStore.service.OrderDetailService;
import com.FurnitureStore.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping("/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/orders")
	public String list() {
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("totalItem", orderDetailService.totalItem(id));
		model.addAttribute("totalPrice", orderDetailService.totalPrice(id));
		return "order/detail";
	}
	
	@RequestMapping("/order/details/{id}")
	public String details(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("totalItem", orderDetailService.totalItem(id));
		model.addAttribute("totalPrice", orderDetailService.totalPrice(id));
		return "test";
	}
	
}
