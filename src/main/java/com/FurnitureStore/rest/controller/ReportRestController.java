package com.FurnitureStore.rest.controller;

import com.FurnitureStore.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reports")
public class ReportRestController {

	@Autowired
	private ReportService reportService;
	
//	@GetMapping("/products")
//	public List<Product> listProducts(){
//		return reportService.getTopListProducts();
//	}
	
}
