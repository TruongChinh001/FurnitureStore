package com.FurnitureStore.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.service.AccountService;
import com.FurnitureStore.utility.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bytebuddy.utility.RandomString;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	@GetMapping("/change-password")
	public String changePasswod(Model model, HttpServletRequest req) {
		String username = req.getRemoteUser();
		Account account = accountService.getById(username);

		model.addAttribute("account", account);

		return "user/change_password";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPasswordForm() {
		return "user/forgot_password";
	}
	
	@PostMapping("/forgot-password")
	public String forgotPasswordAction(HttpServletRequest req, //
			Model model, //
			RedirectAttributes ra) throws UnsupportedEncodingException, MessagingException {
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		
		String token = "";
		
		for(int i = 0; i < 6; i++) {
			String random = String.valueOf(accountService.getRandomNumberUsingNextInt(0, 9));
			token += random;
		}
		
		try {
			accountService.updateResetPassword(token, username, email);
			Account account = accountService.findByUsernameAndEmail(username, email);
			accountService.sendResetPasswordCode(account);
			ra.addAttribute("username", account.getUsername());
			return "redirect:/forgot-password/{username}";
		} catch (AccountNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			return "user/forgot_password";
		}
		
//		return "redirect:/forgot-password/verify";
	}
	
	@GetMapping("/forgot-password/{username}")
	public String forgotPasswordVerifyForm(@PathVariable("username") String username, //
			Model model) {
		model.addAttribute("username", username);
		return "user/forgot_password_verify";
	}
	
	@PostMapping("/forgot-password/{username}")
	public String forgotPasswordVerifyAction(HttpServletRequest req, //
			@PathVariable("username") String username, //
			Model model, //
			RedirectAttributes ra) {
		String resetPasswordCode = req.getParameter("verify");
		
		Account account = accountService.getById(username);
		
		if(resetPasswordCode.equals(account.getResetPasswordCode())) {
			ra.addAttribute("username", account.getUsername());
			return "redirect:/forgot-password/new-password/{username}";
		} else {
			model.addAttribute("message", "Mã xác nhận không hợp lệ");
			return "user/forgot_password_verify";
		}
	}
	
	@GetMapping("/forgot-password/new-password/{username}")
	public String newPasswordForm(@PathVariable("username") String username, //
			Model model) {
		model.addAttribute("username", username);
		return "user/new_password";
	}
	
	@PostMapping("/forgot-password/new-password/{username}")
	public String newPasswordAction(@PathVariable("username") String username, //
			HttpServletRequest req, //
			Model model, //
			RedirectAttributes ra) {
		
		Account account = accountService.getById(username);
		
		String newPassword = req.getParameter("newPassword");
		String confirmNewPassword = req.getParameter("confirmNewPassword");
		
		if(newPassword.equals(confirmNewPassword)) {
			accountService.updatePassword(account, newPassword);
			model.addAttribute("success", "Bạn đã đặt lại mật khẩu thành công!");
			ra.addAttribute("username", account.getUsername());
		} else {
			model.addAttribute("message", "Xác nhận mật khẩu không chính xác");
			ra.addAttribute("username", account.getUsername());
		}
		
		return "user/new_password";
	}
	
}
