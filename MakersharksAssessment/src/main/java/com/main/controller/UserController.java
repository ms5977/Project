package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.User;
import com.main.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User",description = "Create new user and use that credentials for Authenticate and get the access of other API ")
public class UserController {
	
	@Autowired
	private UserService authService;
	@Operation(summary = "For creating a new user",
			   tags = {"/saveUser","post"}
			
			)
	@PostMapping("/saveUser")
	public ResponseEntity<User>createUser(@RequestBody User authDetails)
	{
		User createToken = authService.createUser(authDetails);
		return new ResponseEntity<User>(createToken,HttpStatus.OK);
		
	}
}
