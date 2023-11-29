package com.test.b2chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequestDto {

	private String userName;

	private String pass;
	
	private String deviceId;
	
	private String playerId;
}
