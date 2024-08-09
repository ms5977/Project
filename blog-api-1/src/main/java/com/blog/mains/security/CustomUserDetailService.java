package com.blog.mains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.mains.entity.Users;
import com.blog.mains.exception.ResourceNotFoundException;
import com.blog.mains.repositary.UserRep;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private  UserRep userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("4");
		Users user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "username"+username, 0));
		System.out.println(username);
		UserPrincipal principal=new UserPrincipal(user);
		
		return principal;
	}

}
