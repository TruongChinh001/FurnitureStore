package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Integer id);

	List<Order> findByUsername(String username);

}
