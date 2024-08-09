package com.blog.mains.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.mains.exception.ApiException;
import com.blog.mains.payloads.JwtAuthResponse;
import com.blog.mains.payloads.LoginUserAuthRequest;
import com.blog.mains.payloads.UserDto;
import com.blog.mains.security.CustomUserDetailService;
import com.blog.mains.security.JwtHelper;
import com.blog.mains.security.UserPrincipal;
import com.blog.mains.services.UserServices;

import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "http://localhost:8081")
public class AuthControllers {
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private ModelMapper mapper;
	
//	@CrossOrigin
	@PostMapping("/authenticate")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody LoginUserAuthRequest loginUserAuthRequest)
	{
//		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
//		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//		if (authenticate.isAuthenticated()) {
//			return jwtHelper.genrateToken(customUserDetailService.loadUserByUsername(loginUser.getUsername()));
//		}
//		else {
//			throw new UsernameNotFoundException("Invalid Credentials");
//		}
		
		
		this.authenticate(loginUserAuthRequest.getUsername(), loginUserAuthRequest.getPassword());
		UserDetails userDetails = customUserDetailService.loadUserByUsername(loginUserAuthRequest.getUsername());
		String token = jwtHelper.genrateToken(userDetails);
		
		JwtAuthResponse authResponse=new JwtAuthResponse();
		authResponse.setToken(token);
		UserPrincipal principal=(UserPrincipal) userDetails;
//		System.out.println("Principal"+principal.getUsername());
		UserDto userDto = this.mapper.map(principal.getUsers(), UserDto.class);
//		authResponse.setUserDto(userDto);
		authResponse.setUserDto(userDto);
		
//		authResponse.setUserDto(this.mapper.map(principal.getUsername(), UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
	}
	public void authenticate(String username,String password)
	{
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try {
			 authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException ex) {
			throw new ApiException("Invalid username and Password");
		}
	}

}
