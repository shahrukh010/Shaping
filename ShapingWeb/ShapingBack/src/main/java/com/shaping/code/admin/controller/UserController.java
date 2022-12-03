package com.shaping.code.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shaping.code.admin.service.UserException;
import com.shaping.code.admin.service.UserService;
import com.shaping.entity.User;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRestController userRest;

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserException {

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);

	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> listAllUsers() {

		return new ResponseEntity<List<User>>(userService.listAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<User> findUserById(@PathVariable(name = "Id") long id) {

		return new ResponseEntity<User>(userService.get(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}

}
