package com.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.User;

public interface UserRepo  extends JpaRepository<User, String>
{

  public Optional<User> findByEmail(String email);

}
