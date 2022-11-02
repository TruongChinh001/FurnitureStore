package com.FurnitureStore.service.impl;

import com.FurnitureStore.model.Role;
import com.FurnitureStore.repository.RoleRepository;
import com.FurnitureStore.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository repo;

	@Override
	public Role getById(String id) {
		return repo.findById(id).get();
	}

}
