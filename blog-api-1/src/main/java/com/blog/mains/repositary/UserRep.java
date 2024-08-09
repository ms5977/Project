package com.blog.mains.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.mains.entity.Users;

public interface UserRep extends JpaRepository<Users, Integer>
{
	public Optional<Users> findByEmail(String username);
}
