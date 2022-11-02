package com.FurnitureStore.controller;

import java.util.Date;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.AccountRegistrationDTO;
import com.FurnitureStore.model.Role;
import com.FurnitureStore.service.AccountService;
import com.FurnitureStore.service.AuthorityService;
import com.FurnitureStore.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AuthorityService authorityService;

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}
	
	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		return "redirect:/";
	}
	
	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "user/login";
	}
	
	@RequestMapping("/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất");
		return "user/login";
	}
	
	@RequestMapping("/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất");
		return "user/login";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("account", new AccountRegistrationDTO());
		return "user/register";
	}
	
	@PostMapping("/register")
	public String registesUserAccount(Model model, @ModelAttribute("account") AccountRegistrationDTO registrationDto) {
		Account account = accountService.convertToEntity(registrationDto);
		account.setEnable(true);
		account.setCreateDate(new Date());
		accountService.save(account);
		Account accountSaved = accountService.getById(account.getUsername());
		Role roleDefault = roleService.getById("GUEST");
		authorityService.saveNew(accountSaved, roleDefault);
		model.addAttribute("message", "Đăng ký thành công!");
		return "user/register_success";
	}
	
}
