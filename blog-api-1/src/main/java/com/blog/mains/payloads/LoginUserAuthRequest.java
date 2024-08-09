package com.blog.mains.payloads;

import lombok.Data;

@Data
public class LoginUserAuthRequest 
{
	private String username;
	private String password;
}
