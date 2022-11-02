package com.FurnitureStore.service.impl;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.Authority;
import com.FurnitureStore.model.Role;
import com.FurnitureStore.repository.AuthorityRepository;
import com.FurnitureStore.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	AuthorityRepository repo;

	@Override
	public Authority saveNew(Account account, Role role) {
		Authority auth = new Authority();
		auth.setAccount(account);
		auth.setRole(role);
		return repo.save(auth);
	}

}
