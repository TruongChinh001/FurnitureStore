package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.CategoryGroup;
import com.FurnitureStore.service.CategoryGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/categories-group")
public class CategoryGroupRestController {

	@Autowired
	private CategoryGroupService categoryGroupService;
	
	@GetMapping
	public List<CategoryGroup> list(){
		return categoryGroupService.findAll();
	}
	
}
