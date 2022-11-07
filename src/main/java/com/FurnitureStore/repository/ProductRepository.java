package com.FurnitureStore.repository;

import java.util.List;

import com.FurnitureStore.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	List<Product> findByCategory(Integer cid);

	@Query("SELECT p FROM Product p WHERE p.category.categoryGroup.id = ?1")
	List<Product> findByCategoryGroup(Integer id);

	@Query("SELECT COUNT(p) FROM Product p")
	Integer getCountProducts();

	@Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = ?1")
	Integer getCountProductByCategory(Integer id);

//	@Query(value = "SELECT * FROM products WHERE create_date BETWEEN NOW() - INTERVAL 15 DAY AND NOW()", nativeQuery = true)
    @Query(value = "SELECT * FROM Products ORDER BY create_date LIMIT 8", nativeQuery = true)
	List<Product> getNewProduct();

}
