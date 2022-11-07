package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.CategoryGroup;
import com.FurnitureStore.repository.CategoryGroupRepository;
import com.FurnitureStore.service.CategoryGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryGroupServiceImpl implements CategoryGroupService{

	@Autowired
	private CategoryGroupRepository repo;
	
	@Override
	public List<CategoryGroup> findAll() {
		return repo.findAll();
	}
	
}
