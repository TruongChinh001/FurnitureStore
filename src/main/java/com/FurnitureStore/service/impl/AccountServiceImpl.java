package com.FurnitureStore.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.model.AccountRegistrationDTO;
import com.FurnitureStore.repository.AccountRepository;
import com.FurnitureStore.service.AccountService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Override
	public Account convertToEntity(AccountRegistrationDTO userRegistrationDTO) {
		return mapper.map(userRegistrationDTO, Account.class);
	}
	
	@Override
	public List<Account> getAll() {
		return repo.findAll();
	}

	@Override
	public Account getById(String username) {
		return repo.findById(username).get();
	}

	@Override
	public Account save(Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		return repo.save(account);
	}

	@Override
	public Account create(Account account) {
		return repo.save(account);
	}

	@Override
	public Account update(Account account) {
		return repo.save(account);
	}

	@Override
	public void delete(String username) {
		repo.deleteById(username);
	}

	@Override
	public List<Account> getAdministrators() {
		return repo.getAdministrator();
	}

	@Override
	public void updateResetPassword(String token, String username, String email) throws AccountNotFoundException {
		
		Account account = repo.findByUsernameAndEmail(username, email);
		
		if(account != null) {
			account.setResetPasswordCode(token);
			repo.save(account);
		} else {
			throw new AccountNotFoundException("Email hoặc tài khoản không tồn tại ");
		}
		
	}

	@Override
	public Account getAccountByResetPasswordCode(String token) {
		return repo.findByResetPasswordCode(token);
	}

	@Override
	public void updatePassword(Account account, String newPassword) {
		account.setPassword(bCryptPasswordEncoder.encode(newPassword));
		account.setResetPasswordCode(null);
		
		repo.save(account);
	}
	
	@Override
	public int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}

	@Override
	public void sendResetPasswordCode(Account account) throws UnsupportedEncodingException, MessagingException {
		String subject = "Mã xác nhận đặt lại mật khẩu";
		String senderName = "Furniture Store";
		
		String mailContent = "<p><i>Xin chào</i><b> " + account.getFullname() + "</b>,</p>";
		mailContent += "<p>Mã xác nhận đặt lại mật khẩu của bạn là:</p>";
		
		String token = account.getResetPasswordCode();
		mailContent += "<h3>"+ token + "</h3>";
		
		mailContent += "<p>Cảm ơn <br>Cửa hàng nội thất Furniture Store</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("fteam.net", senderName);
		helper.setTo(account.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		
		mailSender.send(message);
	}

	@Override
	public Account findByUsernameAndEmail(String username, String email) {
		return repo.findByUsernameAndEmail(username, email);
	}
	
}
