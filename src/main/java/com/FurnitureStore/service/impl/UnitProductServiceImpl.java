package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.UnitProduct;
import com.FurnitureStore.repository.UnitProductRepository;
import com.FurnitureStore.service.UnitProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitProductServiceImpl implements UnitProductService{

	@Autowired
	private UnitProductRepository repo;
	
	@Override
	public List<UnitProduct> getAll() {
		return repo.findAll();
	}

	
	
}
