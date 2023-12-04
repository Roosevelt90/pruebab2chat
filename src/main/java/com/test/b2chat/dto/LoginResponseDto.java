package com.test.b2chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class LoginResponseDto {

	private String userName;
	
	private String email;
	
	private String token;
	
	private String mensajeError;

	public LoginResponseDto(String userName, String email, String token, String mensajeError) {
		super();
		this.userName = userName;
		this.email = email;
		this.token = token;
		this.mensajeError = mensajeError;

	}

	public LoginResponseDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
