package com.blog.mains.services;
import java.util.List;

import com.blog.mains.payloads.UserDto;

public interface UserServices {
	public UserDto createUser(UserDto userDto);
	public UserDto getUserByEmail(String username);
	public List<UserDto>getAllUser();
	public void deleteUserById(Integer userId);
	public UserDto updateUser(String username,UserDto userDto);
	public List<UserDto>saveMultipleUser(List<UserDto>userDtos);
}
