package com.FurnitureStore.repository;

import java.util.List;

import com.FurnitureStore.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("SELECT c FROM Category c WHERE c.categoryGroup.id = ?1")
	List<Category> findAllByCategoyGroup(Integer id);
	
	
}
