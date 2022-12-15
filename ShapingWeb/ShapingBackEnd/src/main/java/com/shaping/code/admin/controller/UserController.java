package com.shaping.code.admin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserException, IOException {
//		System.out.println(multipartFile.getOriginalFilename());

//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = "user-photo";

		// FileUtil.uploadFile(uploadDir, fileName, multipartFile);
//		S3Util.uploadFile(fileName, multipartFile.getInputStream());
//		FileUtil.uploadFile(uploadDir, fileName, multipartFile);

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

	@GetMapping("/status/{id}")
	public ResponseEntity<String> updateUserStatus(@PathVariable(name = "id") long id, boolean status) {

		return new ResponseEntity<String>(userService.updateUserStatus(id, status), HttpStatus.OK);
	}

	@GetMapping("/page/pageNo}")
	public ResponseEntity<List<User>> listByPage(@PathVariable(name = "pageNo") int pageNo) {

		return new ResponseEntity<List<User>>(userService.listByPage(pageNo), HttpStatus.OK);
	}
	
	@GetMapping("/page/sort")
	public ResponseEntity<List<User>>listByPageAndSort(
	@RequestParam(value = "sortField")String sortField,@RequestParam(value = "sortDir")String sortDir){
		
		return new ResponseEntity<List<User>>(userService.listByPageAndSort(0, sortField, sortDir),HttpStatus.OK);
	}
	

}
