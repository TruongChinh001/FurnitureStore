package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Role;

public interface RoleService {

	Role getById(String id);

	List<Role> getAll();

}
