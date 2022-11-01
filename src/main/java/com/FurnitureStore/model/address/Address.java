package com.FurnitureStore.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.FurnitureStore.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Boolean isDefault;
	
	@Column(name = "address_line_1", nullable = false, length = 255)
	private String addressLineOne;
	
	@ManyToOne()
	@JoinColumn(name = "account_id")
	private Account account;
	
	@ManyToOne()
	@JoinColumn(name = "province_city_id")
	private ProvinceCity provinceCity;
	
	@ManyToOne()
	@JoinColumn(name = "district_id")
	private District district;
	
	@ManyToOne()
	@JoinColumn(name = "hamlet_id")
	private Hamlet hamlet;
	
	@ManyToOne()
	@JoinColumn(name = "ward_village_id")
	private WardVillage wardVillage;
	
}
