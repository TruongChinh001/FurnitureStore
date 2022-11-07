package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.address.District;
import com.FurnitureStore.model.address.ProvinceCity;

public interface AddressService {

	List<ProvinceCity> getAllProvinceCity();

	List<District> getAllDistrictByProvinceCity(Integer id);

}
