package com.FurnitureStore.controller;

import java.util.List;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@GetMapping()
	public String account(Model model) {
		List<Account> list = accountService.getAll();
		model.addAttribute("list", list);
		return "test";
	}
	
}
