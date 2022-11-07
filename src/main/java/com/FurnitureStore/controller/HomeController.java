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
		
		
		List<Product> livingRooms = productService.getProductsInCategoryGroup(1);
		model.addAttribute("livingRooms", livingRooms);
		
		List<Product> workRooms = productService.getProductsInCategoryGroup(5);
		model.addAttribute("workRooms", workRooms);
		
		List<Product> bedRooms = productService.getProductsInCategoryGroup(4);
		model.addAttribute("bedRooms", bedRooms);
		
		List<Product> diningRooms = productService.getProductsInCategoryGroup(2);
		model.addAttribute("diningRooms", diningRooms);
		
		List<Product> decorated = productService.getProductsInCategoryGroup(6);
		model.addAttribute("decorated", decorated);
		
		return "index";
	}
	
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}

}
