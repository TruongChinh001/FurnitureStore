package com.FurnitureStore.service.impl;

import com.FurnitureStore.repository.OrderDetailRepository;
import com.FurnitureStore.service.OrderDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepository repo;
	
	@Override
	public Integer totalItem(Integer id) {
		return repo.totalItem(id);
	}

	@Override
	public Float totalPrice(Integer id) {
		return repo.totalPrice(id);
	}

}
