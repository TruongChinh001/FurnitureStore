package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.Material;
import com.FurnitureStore.service.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/materials")
public class MaterialRestController {

	@Autowired
	private MaterialService materialService;
	
	@GetMapping()
	public List<Material> getAll(){
		return materialService.getAll();
	}
	
}
