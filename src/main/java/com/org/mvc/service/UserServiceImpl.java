package com.org.mvc.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.mvc.dto.RegistrationDto;
import com.org.mvc.models.Role;
import com.org.mvc.models.UserEntity;
import com.org.mvc.repository.RoleRepository;
import com.org.mvc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void saveUser(RegistrationDto registrationDto) {
		UserEntity user= new UserEntity();
		user.setUsername(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		Role role= roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
		
	}



	@Override
	public UserEntity findbyEmail(String email) {
		
		return userRepository.findByEmail(email);
	}



	@Override
	public UserEntity findbyUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	

}
