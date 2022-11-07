package com.FurnitureStore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.FurnitureStore.model.address.ProvinceCity;
import com.FurnitureStore.service.AccountService;
import com.FurnitureStore.service.AddressService;
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
	private AccountService accountService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping("/checkout")
	public String checkout(Model model, HttpServletRequest req) {
		String username = req.getRemoteUser();
		model.addAttribute("remote", accountService.getById(username));
		
		List<ProvinceCity> provinceCities = addressService.getAllProvinceCity();
		model.addAttribute("provinceCities", provinceCities);
		
		return "order/checkout";
	}
	
	@RequestMapping("/your-orders")
	public String list(Model model, HttpServletRequest req) {
		String username = req.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("totalItem", orderDetailService.totalItem(id));
		model.addAttribute("totalPrice", orderDetailService.totalPrice(id));
		return "order/detail";
	}
	
}
