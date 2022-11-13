package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.address.District;
import com.FurnitureStore.model.address.ProvinceCity;
import com.FurnitureStore.repository.address.DistrictRepository;
import com.FurnitureStore.repository.address.ProvinceCityRepository;
import com.FurnitureStore.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private ProvinceCityRepository provinceCityRepo;
	
	@Autowired
	private DistrictRepository districtRepo;

	@Override
	public List<ProvinceCity> getAllProvinceCity() {
		return provinceCityRepo.findAll();
	}

	@Override
	public List<District> getAllDistrictByProvinceCity(Integer id) {
		return districtRepo.findAllByProvinceCity(id);
	}

	@Override
	public ProvinceCity getOneProvinceCity(Integer id) {
		return provinceCityRepo.findById(id).get();
	}

}
