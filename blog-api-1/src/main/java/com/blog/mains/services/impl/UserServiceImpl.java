package com.blog.mains.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.blog.mains.config.AppConstant;
import com.blog.mains.entity.Role;
import com.blog.mains.entity.Users;
import com.blog.mains.exception.ResourceNotFoundException;
import com.blog.mains.payloads.RoleDto;
import com.blog.mains.payloads.UserDto;
import com.blog.mains.repositary.RoleRepo;
import com.blog.mains.repositary.UserRep;
import com.blog.mains.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRep userRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		Users user = this.modelMapper.map(userDto, Users.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		this.modelMapper.map(user, null)
		Role role = this.roleRepo.findById(AppConstant.USER).get();
		user.getRoles().add(role);
		Users saveUser = this.userRepo.save(user);
		
		
		return this.modelMapper.map(saveUser, UserDto.class);
	}
//	@Override
//	public UserDto getUserByEmail(String username) {
//		Users user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("username", "Email"+username, 0));
//		System.out.println(user);
//		return modelMapper.map(user, UserDto.class);
//	}
	public UserDto getUserByEmail(String username) {
	    Users user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("username", "Email" + username, 0));
	    System.out.println(user);

	    // Create a new instance of UserDto
	    UserDto userDto = modelMapper.map(user, UserDto.class);
//	    UserDto userDto = new UserDto();

	    // Map simple fields
//	    userDto.setId(user.getId());
//	    userDto.setName(user.getName());
//	    userDto.setEmail(user.getEmail());
//	    userDto.setPassword(user.getPassword());
//	    userDto.setAbout(user.getAbout());
	   

	    // Manually map roles
	    Set<RoleDto> roleDtos = user.getRoles().stream()
	            .map(role -> modelMapper.map(role, RoleDto.class))
	            .collect(Collectors.toSet());
	    userDto.setRoleDtos(roleDtos);

	    System.out.println(userDto.getRoleDtos()); // Verify roleDtos mapping
	    return userDto;
	}
	@Override
	public List<UserDto> getAllUser() {
		List<Users> usersList = userRepo.findAll();
//		List<UserDto> userDtos = usersList.stream().map((user)->this.modelMapper.map(user, UserDto.class)).toList();
		List<UserDto> userDtos= usersList.stream().map(user->{
			UserDto userDto = modelMapper.map(user, UserDto.class);
			Set<RoleDto> roleDtos = user.getRoles().stream().map(role->modelMapper.map(role, RoleDto.class))
			.collect(Collectors.toSet());
			userDto.setRoleDtos(roleDtos);
			
			return userDto;
		}).toList();
		
		return userDtos;
	}
	@Override
	public void deleteUserById(Integer userId) {
		Users user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		this.userRepo.delete(user);
		
	}
	@Override
	public UserDto updateUser(String username, UserDto userDto) {
//		Users user = this.userRepo.findById(userId).orElse(null);
		Users user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "UserId"+username, 0));
		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());
		Users updateUser = this.userRepo.save(user);
		return this.modelMapper.map(updateUser, UserDto.class);
	}
	
	@Override
	public List<UserDto> saveMultipleUser(List<UserDto> userDtos) {
		List<Users> user = userDtos.stream().map((userDtoList)->this.modelMapper.map(userDtoList, Users.class)).toList();
		List<Users> savedUser = this.userRepo.saveAll(user);
		
		return savedUser.stream()
						.map((savedUserList)->this.modelMapper.map(savedUserList, UserDto.class))
						.toList();
	}

}
