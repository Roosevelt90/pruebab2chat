package com.test.b2chat.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class LoginResponseDto {

	private String idUsuario;
	
	private String nombres;

	private String apellidos;

	private String email;

	private String nombreUsuario;
	
	private String idEmpresa;
   
	private String nombreEmpresa;
	
	private String token;
	
	private String mensajeError;
	
	private String password;

	public LoginResponseDto(Long idUsuario, String nombres, String apellidos, String email, String nombreUsuario, Long idEmpresa,
			String nombreEmpresa, String password) {
		super();
		this.idUsuario = idUsuario.toString();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.idEmpresa = idEmpresa.toString();
		this.nombreEmpresa = nombreEmpresa;
		this.password = password;
	}

	public LoginResponseDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
