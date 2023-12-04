package com.test.b2chat.iservices;

import com.test.b2chat.dto.LoginRequestDto;
import com.test.b2chat.dto.LoginResponseDto;
import com.test.b2chat.entities.User;

public interface ILoginService {

	public LoginResponseDto loginService(User loginRequest);
	
}
