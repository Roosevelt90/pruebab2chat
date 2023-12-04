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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements ILoginService{

    @Autowired
	private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
	public LoginResponseDto loginService(User loginRequest) {	

		User user = userDao.findByEmail(loginRequest.getEmail());
		LoginResponseDto rsp = new LoginResponseDto();
		if(user == null) {
			rsp.setMensajeError("Usuario no existe");
			return rsp;
		}
		
		if(!this.isPasswordValid(loginRequest.getPassword(), user.getPassword())) {			
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
	
	public String getJWTToken(String username) {
		String token = "";
		try {
			String secretKey = "mySecretKey";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
			token = Jwts
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
		    
			
		} catch (Exception e) {
			System.out.println("-----e-------" + e.getMessage());
		}
		return "Bearer " + token;
	}

}

