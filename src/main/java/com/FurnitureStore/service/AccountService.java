package com.FurnitureStore.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;

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
	
	void updateResetPassword(String token, String username, String email) throws AccountNotFoundException;
	
	Account getAccountByResetPasswordCode(String token);
	
	void updatePassword(Account account, String newPassword);
	
	Account findByUsernameAndEmail(String username, String email);

	int getRandomNumberUsingNextInt(int min, int max);

	void sendResetPasswordCode(Account account) throws UnsupportedEncodingException, MessagingException;
	
}
