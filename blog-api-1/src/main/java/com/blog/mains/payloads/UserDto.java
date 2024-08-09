package com.blog.mains.payloads;

import java.util.HashSet;
import java.util.Set;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	
	private int id;
	
	@NotEmpty(message = "Name Must Not be Empty")
	@Size(min = 4)
	private String name;
	@NotBlank(message = "Email is mandtory")
//	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$\r\n"+ "",message = "Please Provide Valid Email")
	private String email;
	
	@NotEmpty
	@Size(min = 4,max = 12)
	private String password;
	
	@NotBlank(message = "About Must Not be Null")
	private String about;
	
	private Set<RoleDto>roleDtos=new HashSet<RoleDto>();
}
