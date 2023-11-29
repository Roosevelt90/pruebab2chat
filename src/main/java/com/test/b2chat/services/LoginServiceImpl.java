package com.test.b2chat.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.b2chat.dto.LoginRequestDto;
import com.test.b2chat.dto.LoginResponseDto;
import com.test.b2chat.entities.User;
import com.test.b2chat.iservices.ILoginService;
import com.test.b2chat.repository.UserDao;
import com.test.b2chat.util.EncryptionComponent;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements ILoginService{

    @Autowired
	private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final EncryptionComponent encryptionComponent;

    @Autowired
    public LoginServiceImpl(EncryptionComponent encryptionComponent) {
    	this.encryptionComponent = encryptionComponent;
    }
    
	@Override
	public LoginResponseDto loginService(LoginRequestDto loginRequest) {	

		User user = userDao.findByEmail(loginRequest.getUserName());
		LoginResponseDto rsp = new LoginResponseDto();
		if(user == null) {
			rsp.setMensajeError("Usuario no existe o esta inactivo");
			return rsp;
		}
		
		if(!this.isPasswordValid(loginRequest.getPass(), user.getPassword())) {			
			rsp.setMensajeError("Nombre de usuario y/o contraseña incorrecta");
			return rsp;
		}
					
		String token = getJWTToken(user.getEmail());	
		rsp.setEmail(user.getEmail());
		rsp.setToken(token);
		
		return rsp;
	}

	public boolean isPasswordValid(String rawPassword, String encodedPassword) {
	    return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
	    
		return "Bearer " + token;
	}

}

