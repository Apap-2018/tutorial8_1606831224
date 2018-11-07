package com.apap.tutorial8.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	private String updatePaswordUser() {
		return "update-password";
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	private String updatePaswordUserBaru(HttpServletRequest request,
			@RequestParam(value = "passwordLama") String passwordLama,
			@RequestParam(value = "passwordBaru") String passwordBaru,
			@RequestParam(value = "passwordBaru2") String passwordBaru2) {
		String username = request.getRemoteUser();
		System.out.println(userService.checkPass(passwordLama, username));
		System.out.println(passwordLama);
		if (userService.checkPass(passwordLama, username)) {
			if (passwordBaru.equals(passwordBaru2)) {
				userService.updateUser(username, passwordBaru);
			}
		}
		
		return "update-password";
	}
	
	
}
