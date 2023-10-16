package com.org.mvc.dto;



import javax.validation.constraints.NotEmpty;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDto
{
	
	private Long id;
	
	@NotEmpty
	private String username;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	

}
