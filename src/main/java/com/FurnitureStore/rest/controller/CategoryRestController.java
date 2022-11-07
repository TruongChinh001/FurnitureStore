package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.Category;
import com.FurnitureStore.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/category-groups/{id}")
	public List<Category> listCategory(@PathVariable("id") Integer id){
		return categoryService.findAllCategoryByCategoryGroup(id);
	}
	
}
