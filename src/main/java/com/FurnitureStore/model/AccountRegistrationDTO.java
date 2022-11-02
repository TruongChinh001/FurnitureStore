package com.FurnitureStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistrationDTO {

	private String username;
	private String fullname;
	private String password;
	private String confirmPassword;
	private String email;
	private String phoneNumber;
	
}
