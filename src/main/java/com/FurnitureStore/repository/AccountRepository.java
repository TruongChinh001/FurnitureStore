package com.FurnitureStore.repository;

import com.FurnitureStore.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String>{

}
