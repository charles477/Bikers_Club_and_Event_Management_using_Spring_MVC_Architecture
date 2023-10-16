package com.org.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.mvc.dto.RegistrationDto;
import com.org.mvc.models.UserEntity;
import com.org.mvc.service.UserService;

@Controller
public class AuthController 
{
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/register")
	public String getRegisterForm(Model model)
	{
		RegistrationDto user= new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
		
		
	}
	
	 @PostMapping("/register/save")
	    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
	                           BindingResult result, Model model) {
	        UserEntity existingUserEmail = userService.findbyEmail(user.getEmail());
	        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
	           return "redirect:/register?fail";
	        }
	        UserEntity existingUserUsername = userService.findbyUsername(user.getUsername());
	        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
	            return "redirect:/register?fail";
	        }
	        if(result.hasErrors()) {
	            model.addAttribute("user", user);
	            return "register";
	        }
	        userService.saveUser(user);
	        return "redirect:/clubs?success";
	    }
	 @GetMapping("/login")
	 
	 public String loginPage()
	 {
		 return "login";
	 }
}
