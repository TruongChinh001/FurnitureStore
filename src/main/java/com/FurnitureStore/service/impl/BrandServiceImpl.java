package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Brand;
import com.FurnitureStore.repository.BrandRepository;
import com.FurnitureStore.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandRepository repo;

	@Override
	public List<Brand> findAll() {
		return repo.findAll();
	}

}
