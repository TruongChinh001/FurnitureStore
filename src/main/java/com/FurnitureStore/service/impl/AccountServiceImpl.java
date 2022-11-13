package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.AccountRegistrationDTO;
import com.FurnitureStore.repository.AccountRepository;
import com.FurnitureStore.service.AccountService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public Account convertToEntity(AccountRegistrationDTO userRegistrationDTO) {
		return mapper.map(userRegistrationDTO, Account.class);
	}
	
	@Override
	public List<Account> getAll() {
		return repo.findAll();
	}

	@Override
	public Account getById(String username) {
		return repo.findById(username).get();
	}

	@Override
	public Account save(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		return repo.save(account);
	}

	@Override
	public Account create(Account account) {
		return repo.save(account);
	}

	@Override
	public Account update(Account account) {
		return repo.save(account);
	}

	@Override
	public void delete(String username) {
		repo.deleteById(username);
	}

	@Override
	public List<Account> getAdministrators() {
		return repo.getAdministrator();
	}
	
}
