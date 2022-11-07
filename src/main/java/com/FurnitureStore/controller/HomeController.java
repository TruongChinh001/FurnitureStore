package com.FurnitureStore.controller;

import java.util.List;

import com.FurnitureStore.model.Product;
import com.FurnitureStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
		List<Product> newProducts = productService.getNewProduct();
		model.addAttribute("newProducts", newProducts);
		
		List<Product> featuredProducts = productService.findAll();
		model.addAttribute("featuredProducts", featuredProducts);
		model.addAttribute("message", "dmmm");
		return "index";
	}
	
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}

}
