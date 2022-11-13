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

	@Override
	public Brand findById(Integer bid) {
		return repo.findById(bid).get();
	}

	@Override
	public Brand create(Brand brand) {
		return repo.save(brand);
	}

	@Override
	public Brand update(Brand brand) {
		return repo.save(brand);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

}
