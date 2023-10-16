package com.org.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.mvc.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> 
{
	UserEntity findByEmail(String email);
	UserEntity findByUsername(String username);
	UserEntity findFirstByUsername(String username);
	

}
