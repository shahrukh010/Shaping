package com.shaping.code.admin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shaping.code.admin.FileUtil;
import com.shaping.code.admin.S3Util;
import com.shaping.code.admin.service.UserException;
import com.shaping.code.admin.service.UserNotFoundException;
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
	public ResponseEntity<User> saveUser(User users, @RequestParam("file") MultipartFile multipartFile)
			throws UserException, IOException {
		System.out.println(multipartFile.getOriginalFilename());

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = "user-photo";

		//FileUtil.uploadFile(uploadDir, fileName, multipartFile);
//		S3Util.uploadFile(fileName, multipartFile.getInputStream());
		FileUtil.uploadFile(uploadDir, fileName, multipartFile);

//		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
		return null;

	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> listAllUsers() {

		return new ResponseEntity<List<User>>(userService.listAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<User> findUserById(@PathVariable(name = "Id") long id) {

		return new ResponseEntity<User>(userService.get(id), HttpStatus.OK);
	}

	/**
	 * 
	 * use mvc and send updateUser response to client for view users data and client
	 * according to their need update and send request to save.
	 */
	@GetMapping("/edit/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "id") long id) {

		return new ResponseEntity<User>(userService.get(id), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable(name = "id") long id) {

		return new ResponseEntity<User>(userService.get(id), HttpStatus.OK);
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<Object> removeById(@PathVariable(name = "id") long id) {

		try {

			return new ResponseEntity<Object>(userService.removeById(id), HttpStatus.OK);
		} catch (UserNotFoundException ex) {

			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
