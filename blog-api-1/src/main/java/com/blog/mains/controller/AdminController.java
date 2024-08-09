package com.blog.mains.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.mains.payloads.ApiResponse;
import com.blog.mains.payloads.UserDto;
import com.blog.mains.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private UserServices userServices;
	
//	Saved MutlipleUser
	@PostMapping("/create-multi-user")
	public ResponseEntity<List<UserDto>>savedMultipleUser(@Valid @RequestBody List<UserDto>userDtos)
	{
		List<UserDto> userDtosList = this.userServices.saveMultipleUser(userDtos);
		return new ResponseEntity<List<UserDto>>(userDtosList,HttpStatus.OK);
	}
		
//	Get All User
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> allUser = this.userServices.getAllUser();
		return new ResponseEntity<List<UserDto>>(allUser,HttpStatus.OK);
	}
//	Delete UserById
	@DeleteMapping("/deleteUserById/{userId}")
	public ResponseEntity<ApiResponse> deleteuserById(@PathVariable Integer userId)
	{
		this.userServices.deleteUserById(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Succesfully", true, HttpStatus.OK, LocalDateTime.now()),HttpStatus.OK);
	}
}
