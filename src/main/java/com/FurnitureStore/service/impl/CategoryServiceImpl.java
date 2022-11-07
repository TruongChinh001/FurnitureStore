package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Category;
import com.FurnitureStore.repository.CategoryRepository;
import com.FurnitureStore.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository repo;
	
	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Category> findAllCategoryByCategoryGroup(Integer id) {
		return repo.findAllByCategoyGroup(id);
	}
	
}
