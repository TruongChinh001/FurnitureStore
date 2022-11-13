package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Material;
import com.FurnitureStore.repository.MaterialRepository;
import com.FurnitureStore.service.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialRepository repo;
	
	@Override
	public List<Material> getAll() {
		return repo.findAll();
	}

}
