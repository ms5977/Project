package com.blog.mains.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.mains.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
