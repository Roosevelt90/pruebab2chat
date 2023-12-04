package com.test.b2chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.b2chat.dto.LoginResponseDto;
import com.test.b2chat.entities.User;
import com.test.b2chat.iservices.ILoginService;



@RestController
@RequestMapping("/autenticacion")
public class LoginController {

	@Autowired
	private ILoginService iLoginService;	

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<LoginResponseDto> login(@RequestBody User request) {
		LoginResponseDto loginResponse = new LoginResponseDto();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			
			loginResponse = iLoginService.loginService(request);
			
			if(loginResponse.getMensajeError() != null) {
			    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
			            .body(loginResponse);
			}
			httpStatus  = HttpStatus.OK;			
		} catch (Exception e) {
			loginResponse.setMensajeError(e.getMessage());
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		            .body(loginResponse);
		} 

	    return ResponseEntity.status(httpStatus)
	            .body(loginResponse);
	}

}
