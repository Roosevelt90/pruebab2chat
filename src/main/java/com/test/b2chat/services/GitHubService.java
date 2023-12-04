package com.test.b2chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.test.b2chat.dto.GitHubInfoDto;
import com.test.b2chat.dto.GithubResponseDto;


@Service
public class GitHubService {

    @Value("${github.api.url}")
    private String githubApiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public GitHubService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<Object> getUserInfo(String username) {
        String url = githubApiUrl + "/users/" + username;        
        GithubResponseDto rsp = new GithubResponseDto();
        try {
        	GitHubInfoDto infoGit = restTemplate.getForObject(url, GitHubInfoDto.class);
        	rsp.setData(infoGit);
            return new ResponseEntity<Object>(rsp, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
            	rsp.setMensajeError("Usuario no encontrado en GitHub");
            	return new ResponseEntity<Object>(rsp, HttpStatus.BAD_REQUEST);                
            } else {
                rsp.setMensajeError("Error grave");
            	return new ResponseEntity<Object>(rsp, HttpStatus.BAD_REQUEST);
            }
        }
    }
}

