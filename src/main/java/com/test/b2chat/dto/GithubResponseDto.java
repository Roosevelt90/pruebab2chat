package com.test.b2chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubResponseDto {
	
	private GitHubInfoDto data;
	
	private String mensajeError;

}
