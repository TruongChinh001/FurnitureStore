package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.Authority;
import com.FurnitureStore.model.Role;

public interface AuthorityService {

	Authority saveNew(Account accountSaved, Role roleDefault);

	List<Authority> findAuthoritiesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority authority);

	void delete(Integer id);

}
