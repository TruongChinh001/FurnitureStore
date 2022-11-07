package com.FurnitureStore.repository;

import com.FurnitureStore.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
