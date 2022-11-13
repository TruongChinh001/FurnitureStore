package com.FurnitureStore.model.address;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Districts")
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "district")
	private List<Address> addresses;
	
	@JsonIgnore
	@OneToMany(mappedBy = "district")
	private List<WardVillage> wardVillages;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "province_city_id")
	private ProvinceCity provinceCity;
	
}
