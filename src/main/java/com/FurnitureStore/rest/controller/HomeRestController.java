package com.FurnitureStore.rest.controller;

import javax.servlet.http.HttpServletRequest;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/remote")
public class HomeRestController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping()
	public Account account(HttpServletRequest req) {
		String username = req.getRemoteUser();
		return accountService.getById(username);
	}
	
}
