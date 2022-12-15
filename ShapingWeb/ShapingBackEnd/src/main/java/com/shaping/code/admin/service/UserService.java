package com.shaping.code.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	 * it is interface its class implementation is BcryptPasswordEncoder
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

	public boolean isEmailUnique(String email) throws UserException {

		User user = userRepo.findByEmail(email);

		if (user != null) {

			throw new UserException("the email address is already associated with another Account");
		}
		return true;
	}

	public User get(long id) {

		return userRepo.findById(id).get();
	}

	public String removeById(long id) throws UserNotFoundException {

		Long flag = userRepo.countById(id);
		if (flag == null || flag == 0) {

			throw new UserNotFoundException("Given user " + id + " not exists");
		} else {
			userRepo.deleteById(id);
		}
		return "removed user:" + id;
	}
	
	
	public String updateUserStatus(long id,boolean status) {
		

		userRepo.updateUserStatus(id, status);
		return status==false ? "user disabled":"user enabled";
	}

}
