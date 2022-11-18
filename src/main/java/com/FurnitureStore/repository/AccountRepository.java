package com.FurnitureStore.repository;

import java.util.List;

import com.FurnitureStore.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String>{

	@Query("SELECT DISTINCT a.account FROM Authority a WHERE a.role.id IN ('ADMIN', 'STAFF', 'GUEST')")
	List<Account> getAdministrator();
	
	@Query("SELECT a FROM Account a WHERE a.username = ?1 AND a.email = ?2")
	Account findByUsernameAndEmail(String username, String email);

	Account findByResetPasswordCode(String token);
	
}
