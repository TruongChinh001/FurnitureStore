package com.FurnitureStore.controller;

import javax.servlet.http.HttpServletRequest;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.service.AccountService;
import com.FurnitureStore.utility.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder be;

	@Autowired
	private SessionUtil session;
	
	@GetMapping("/accounts/change-password")
	public String changePasswod(Model model, HttpServletRequest req) {
		String username = req.getRemoteUser();
		Account account = accountService.getById(username);

		model.addAttribute("account", account);

		return "user/change_password";
	}
	
	
	
}
