package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.Category;
import com.FurnitureStore.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/categories")
	public List<Category> getAll(){
		return categoryService.findAll();
	}
	
	@GetMapping("/categories/{id}")
	public Category getOne(@PathVariable("id") Integer id) {
		return categoryService.findById(id);
	}
	
	@PostMapping("/categories")
	public Category create(@RequestBody Category category) {
		return categoryService.create(category);
	}
	
	@PutMapping("/categories/{id}")
	public Category update(@PathVariable("id") Integer id, @RequestBody Category category) {
		return categoryService.update(category);
	}
	
	@DeleteMapping("/categories/{id}")
	public void delete(@PathVariable("id") Integer id) {
		categoryService.delete(id);
	}
	
}
