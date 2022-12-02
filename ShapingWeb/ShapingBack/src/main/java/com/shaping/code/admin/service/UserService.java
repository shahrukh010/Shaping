package com.shaping.code.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shaping.code.admin.repository.UserRepository;
import com.shaping.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

//****************************************************************************************************

	/*
	 *  it is interface its class 
	 *  implementation is BcryptPasswordEncoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

//****************************************************************************************************

	public List<User> listAllUsers() {

		List<User> listUsers = (List<User>) userRepo.findAll();
		return listUsers;
	}

	public User saveUser(User user) {

		passwordEncoder(user);
		return userRepo.save(user);
	}

	private void passwordEncoder(User user) {

		String rowPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(rowPassword);
	}
	
	
	
	public boolean isEmailUnique(String email)throws UserException {
		
		User user = userRepo.findByEmail(email);
		
		if(user!=null) {
			
			throw new UserException("the email address is already associated with another Account");
		}
		return true;
	}

}
