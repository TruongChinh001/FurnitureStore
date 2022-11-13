package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Product;

import org.springframework.data.domain.Page;

public interface ProductService {
	
	List<Product> findAll();

	List<Product> findByCategoryGroup(Integer integer);

	Product findById(Integer id);

	Integer getCountProducts();
	
	Integer getCountProductsByKeyword(String keyword);

	Integer getCountProductsByCategory(Integer integer);

	List<Product> getNewProduct();

	List<Product> getFeaturedProduct();

	List<Product> getProductsInCategoryGroup(int i);

	Integer getCountProductsByBrand(Integer integer);

	Page<Product> listByPageable(int pageNum, String keyword);

	Page<Product> listByCategory(Integer pageNum, Integer cid);

	Page<Product> listByBrand(Integer pageNum, Integer bid);

	Product create(Product product);

}
