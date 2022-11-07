package com.FurnitureStore.rest.controller;

import com.FurnitureStore.model.Product;
import com.FurnitureStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("{id}")
	public Product getProduct(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
}
