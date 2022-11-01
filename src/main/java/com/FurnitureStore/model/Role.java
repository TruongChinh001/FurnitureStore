package com.FurnitureStore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Roles")
public class Role {

	@Id
	@Column(length = 20, nullable = false)
	private String id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@OneToMany(mappedBy = "role")
	private List<Authority> authorities;
	
}
