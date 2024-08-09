package com.blog.mains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.mains.payloads.UserDto;
import com.blog.mains.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	private UserServices userServices;
//	create User
	@PostMapping("/create-user")
	public ResponseEntity<UserDto>createuser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUser = this.userServices.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.OK);
				
	}
}
