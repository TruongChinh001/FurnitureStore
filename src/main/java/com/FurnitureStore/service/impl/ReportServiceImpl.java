package com.FurnitureStore.service.impl;

import com.FurnitureStore.repository.OrderDetailRepository;
import com.FurnitureStore.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;

	
	
}
