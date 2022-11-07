package com.FurnitureStore.repository;

import com.FurnitureStore.model.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

	@Query("SELECT SUM(od.quantity) FROM OrderDetail od WHERE od.order.id = ?1")
	Integer totalItem(Integer id);

	@Query("SELECT SUM(od.price * od.quantity) FROM OrderDetail od WHERE od.order.id = ?1")
	Float totalPrice(Integer id);

}
