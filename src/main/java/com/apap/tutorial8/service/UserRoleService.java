package com.apap.tutorial8.service;

import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	boolean checkPass(String passwordLama, String username);
	void updateUser(String username, String passwordBaru);
}
