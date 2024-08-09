package com.blog.main.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 3)
	private String name;
	
	@Email
	private String email;
	
//	@JsonIgnore
	@NotEmpty
	@Size(min = 4,max = 10)
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto>roles=new HashSet<RoleDto>();
}
