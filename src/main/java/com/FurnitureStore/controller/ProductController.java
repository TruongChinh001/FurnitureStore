package com.FurnitureStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("products")
	public String products() {
		return "product/list";
	}
	
	@GetMapping("product/detail")
	public String productDetail() {
		return "product/detail";
	}
	
}
