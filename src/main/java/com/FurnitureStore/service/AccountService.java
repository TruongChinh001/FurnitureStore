package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.AccountRegistrationDTO;

public interface AccountService {

	List<Account> getAll();

	Account getById(String username);

	Account save(Account account);

	Account convertToEntity(AccountRegistrationDTO userRegistrationDTO);

	Account create(Account account);

	Account update(Account account);

	void delete(String username);

	List<Account> getAdministrators();
	
}
