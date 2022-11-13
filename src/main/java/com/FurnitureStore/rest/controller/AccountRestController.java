package com.FurnitureStore.rest.controller;

import java.util.List;
import java.util.Optional;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/admin")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		} else {
			return accountService.getAll();
		}
	}
	
	@GetMapping()
	public List<Account> getAll(){
		return accountService.getAll();
	}
	
	@GetMapping("/{username}")
	public Account getAccount(@RequestBody Account account, @PathVariable("username") String username) {
		return accountService.getById(username);
	}
	
	@PostMapping()
	public Account create(@RequestBody Account account) {
		account.setPassword("123");
		account.setEnable(true);
		return accountService.create(account);
	}
	
	@PutMapping("/{username}")
	public Account update(@RequestBody Account account, @PathVariable("username") String username) {
		return accountService.update(account);
	}
	
	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
	
}
