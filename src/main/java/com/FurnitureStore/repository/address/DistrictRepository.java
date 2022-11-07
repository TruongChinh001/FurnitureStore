package com.FurnitureStore.repository.address;

import java.util.List;

import com.FurnitureStore.model.address.District;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictRepository extends JpaRepository<District, Integer>{

	@Query("SELECT d FROM District d WHERE d.provinceCity.id = ?1")
	List<District> findAllByProvinceCity(Integer id);

}
