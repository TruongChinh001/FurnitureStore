package com.FurnitureStore.service;

import com.FurnitureStore.model.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderService {

	Order create(JsonNode orderData);

	Object findById(Integer id);

}
