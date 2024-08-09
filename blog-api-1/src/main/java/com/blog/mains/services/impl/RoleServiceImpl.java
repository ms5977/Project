package com.blog.mains.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mains.config.AppConstant;
import com.blog.mains.entity.Role;
import com.blog.mains.payloads.RoleDto;
import com.blog.mains.repositary.RoleRepo;
import com.blog.mains.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public void savedRoles() {
		RoleDto adminRoleDto=new RoleDto();
		adminRoleDto.setName("ROLE_ADMIN");
		adminRoleDto.setId(AppConstant.ADMIN);
		RoleDto userRoleDto=new RoleDto();
		userRoleDto.setName("ROLE_USER");
		userRoleDto.setId(AppConstant.USER);
		
		Role adminRole = this.mapper.map(adminRoleDto, Role.class);
		Role userRole = this.mapper.map(userRoleDto, Role.class);
		
		List<Role> roles = List.of(adminRole,userRole);
		this.roleRepo.saveAll(roles);
//		this.roleRepo.save(adminRole);
//		this.roleRepo.save(userRole);
		

	}

}
