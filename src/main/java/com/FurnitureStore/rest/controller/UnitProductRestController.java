package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.UnitProduct;
import com.FurnitureStore.service.UnitProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/unit-products")
public class UnitProductRestController {

	@Autowired
	private UnitProductService unitProductService;
	
	@GetMapping()
	public List<UnitProduct> getAll(){
		return unitProductService.getAll();
	}
	
}
