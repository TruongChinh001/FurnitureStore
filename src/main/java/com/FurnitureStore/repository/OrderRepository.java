package com.FurnitureStore.repository;

import java.util.List;

import com.FurnitureStore.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.account.username = ?1")
	List<Order> findByUser(String username);

}
