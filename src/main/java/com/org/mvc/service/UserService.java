package com.org.mvc.service;

import com.org.mvc.dto.RegistrationDto;
import com.org.mvc.models.UserEntity;

public interface UserService
{
	
	void saveUser(RegistrationDto registrationDto);

	UserEntity findbyEmail(String email);

	UserEntity findbyUsername(String username);

}
