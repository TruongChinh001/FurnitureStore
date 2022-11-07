package com.FurnitureStore.model.address;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Provinces_cities")
public class ProvinceCity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	@OneToMany(mappedBy = "provinceCity")
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "provinceCity")
	private List<District> districts;
	
}
