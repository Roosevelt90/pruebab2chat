package com.test.b2chat.iservices;

import com.test.b2chat.dto.LoginRequestDto;
import com.test.b2chat.dto.LoginResponseDto;

public interface ILoginService {

	public LoginResponseDto loginService(LoginRequestDto loginRequest);
	
}
