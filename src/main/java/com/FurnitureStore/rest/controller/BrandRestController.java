package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.Brand;
import com.FurnitureStore.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandRestController {

	@Autowired
	private BrandService brandService;
	
	@GetMapping()
	public List<Brand> getAll(){
		return brandService.findAll();
	}
	
	@GetMapping("{id}")
	public Brand getOne(@PathVariable("id") Integer brand) {
		return brandService.findById(brand);
	}
	
	@PostMapping()
	public Brand create(@RequestBody Brand brand) {
		return brandService.create(brand);
	}
	
	@PutMapping("{id}")
	public Brand update(@RequestBody Brand brand, @PathVariable Integer id) {
		return brandService.update(brand);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		brandService.delete(id);
	}
	
}
