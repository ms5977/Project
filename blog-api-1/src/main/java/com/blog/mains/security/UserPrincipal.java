package com.blog.mains.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.mains.entity.Users;

public class UserPrincipal implements UserDetails {

	private Users users;
	
	public UserPrincipal(Users users) {
//		System.out.println(3);
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = users.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).toList();
		return authorities;
	}

//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return users.getPassword();
//	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getEmail();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}
	public Users getUsers() {
        return users;
    }
	

}
