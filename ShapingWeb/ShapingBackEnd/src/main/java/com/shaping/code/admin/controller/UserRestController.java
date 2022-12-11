package com.shaping.code.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaping.code.admin.service.UserException;
import com.shaping.code.admin.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	
	//for AJAX CALL
	@PostMapping("/user/check_email")
	public boolean isEmailUnique(@Param("email")String email) throws UserException {
		
		return userService.isEmailUnique(email);
	}
}
