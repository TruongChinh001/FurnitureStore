package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.Authority;
import com.FurnitureStore.model.Role;
import com.FurnitureStore.repository.AccountRepository;
import com.FurnitureStore.repository.AuthorityRepository;
import com.FurnitureStore.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	private AuthorityRepository repo;
	
	@Autowired
	private AccountRepository accountRepo;

	@Override
	public Authority saveNew(Account account, Role role) {
		Authority auth = new Authority();
		auth.setAccount(account);
		auth.setRole(role);
		return repo.save(auth);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountRepo.getAdministrator();
		return repo.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return repo.findAll();
	}

	@Override
	public Authority create(Authority authority) {
		return repo.save(authority);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

}
