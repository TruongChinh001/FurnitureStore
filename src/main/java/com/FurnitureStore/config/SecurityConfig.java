package com.FurnitureStore.config;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.FurnitureStore.model.Account;
import com.FurnitureStore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account account = accountService.getById(username);
				String password = account.getPassword();
				String[] roles = account.getAuthorities()
						.stream()
						.map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found");
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests()
			.antMatchers("/order/**", "/checkout", "/your-orders")
			.authenticated().antMatchers("/admin/**")
			.hasAnyRole("ADMIN", "STAFF")
			.antMatchers("/rest/roles")
			.hasRole("ADMIN").anyRequest()
			.permitAll();

		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/login/success", false)
			.failureUrl("/login/error");

		http.rememberMe().
			tokenValiditySeconds(86400);

		http.exceptionHandling()
			.accessDeniedPage("/unauthoried");

		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/logout/success");

	}

}
