package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Brand;

public interface BrandService {

	List<Brand> findAll();

	Brand findById(Integer bid);

	Brand create(Brand brand);

	Brand update(Brand brand);

	void delete(Integer id);

}
