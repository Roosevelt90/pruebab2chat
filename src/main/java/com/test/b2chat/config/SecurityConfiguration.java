package com.test.b2chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.test.b2chat.security.JWTAuthorizationFilter;



@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(requests -> requests
                        .antMatchers(HttpMethod.POST, "/autenticacion/user").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/hola").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/usuario").permitAll()
                        .antMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                        .antMatchers(HttpMethod.GET, "/v3/*").permitAll()
                        .antMatchers(HttpMethod.GET, "/v3/api-docs/*").permitAll()
                        .antMatchers(HttpMethod.GET, "/v3/api-docs/swagger-config/*").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                        .anyRequest().authenticated());
		
        return http.build();
    }

}
