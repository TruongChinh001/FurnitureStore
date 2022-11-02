package com.FurnitureStore.service;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.Authority;
import com.FurnitureStore.model.Role;

public interface AuthorityService {

	Authority saveNew(Account accountSaved, Role roleDefault);

}
