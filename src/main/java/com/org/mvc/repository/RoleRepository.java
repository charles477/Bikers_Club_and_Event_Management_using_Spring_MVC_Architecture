package com.org.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.mvc.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
	Role findByName(String name);
	

}
