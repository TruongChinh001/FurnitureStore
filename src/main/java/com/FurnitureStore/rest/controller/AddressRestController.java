package com.FurnitureStore.rest.controller;

import java.util.List;

import com.FurnitureStore.model.address.District;
import com.FurnitureStore.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressRestController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/rest/address/district-by-city/{id}")
	public List<District> getAllDistrictByCity(@PathVariable("id") Integer id){
		return addressService.getAllDistrictByProvinceCity(id);
	}
	
}
