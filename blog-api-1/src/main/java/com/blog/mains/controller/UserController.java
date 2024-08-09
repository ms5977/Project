package com.blog.mains.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.mains.payloads.UserDto;
import com.blog.mains.services.UserServices;

import jakarta.validation.Valid;

@RequestMapping("/user")
@RestController
//@Controller
//@ResponseBody
public class UserController 
{
	@Autowired
	private UserServices userServices;
	
////	create User
//	@PostMapping("/create-user")
//	public ResponseEntity<UserDto>createuser(@Valid @RequestBody UserDto userDto)
//	{
//		UserDto createUser = this.userServices.createUser(userDto);
//		return new ResponseEntity<UserDto>(createUser,HttpStatus.OK);
//				
//	}
//	update User
	@PutMapping("/update")
	public ResponseEntity<UserDto> updateuser(@RequestBody UserDto userDto)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserDto updateUser = this.userServices.updateUser(username, userDto);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.OK);
	}
	
//	GetByID
	@GetMapping("/userByEmail")
	public ResponseEntity<UserDto> getUserByEmail()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserDto userDto = this.userServices.getUserByEmail(username);
		System.out.println(userDto);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
}
