package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Category;

public interface CategoryService {

	List<Category> findAll();

	List<Category> findAllCategoryByCategoryGroup(Integer id);

	Category findById(Integer cid);

	Category create(Category category);

	Category update(Category category);

	void delete(Integer id);

}
