package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.AccountRegistrationDTO;

public interface AccountService {

	List<Account> getAll();

	Account getById(String username);

	void save(Account account);

	Account convertToEntity(AccountRegistrationDTO userRegistrationDTO);
	
}
