package com.FurnitureStore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.FurnitureStore.model.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {

	@Id
	@Column(nullable = false, length = 50)
	private String username;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(nullable = false, length = 255)
	private String fullname;
	
	@Column(nullable = false, length = 255)
	private String email;
	
	@Column(name = "phone_number", nullable = false, length = 20)
	private String phoneNumber;
	
	@Column(length = 255)
	private String photo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(nullable = false)
	private Boolean enable;
	
	@Column(name = "verification_code", length = 64)
	private String verificationCode;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "account")
	private List<CartItem> cartItems;
	
	@OneToMany(mappedBy = "account")
	private List<Review> reviews;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
}
