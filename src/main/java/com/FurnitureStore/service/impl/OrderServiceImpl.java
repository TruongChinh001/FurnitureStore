package com.FurnitureStore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.FurnitureStore.model.Order;
import com.FurnitureStore.model.OrderDetail;
import com.FurnitureStore.repository.OrderDetailRepository;
import com.FurnitureStore.repository.OrderRepository;
import com.FurnitureStore.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		repo.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
			
		};
		
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream()
				.peek(d -> d.setOrder(order))
				.collect(Collectors.toList());
		orderDetailRepo.saveAll(details);
		
		return order;
	}

	@Override
	public Object findById(Integer id) {
		return repo.findById(id).get();
	}

	
	
}
