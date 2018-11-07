package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Transactional
@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public boolean checkPass(String passwordLama, String username) {
		UserRoleModel user = userDb.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(passwordLama, user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public void updateUser(String username, String passwordBaru) {
		UserRoleModel user = userDb.findByUsername(username);
		String hashedPassword = encrypt(passwordBaru);
		user.setPassword(hashedPassword);	
	}

}
