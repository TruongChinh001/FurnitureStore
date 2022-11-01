package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.repository.AccountRepository;
import com.FurnitureStore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repo;
	
	@Override
	public List<Account> getAll() {
		return repo.findAll();
	}
	
}
