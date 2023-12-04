package com.test.b2chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.b2chat.entities.User;
import com.test.b2chat.iservices.IUserService;
import com.test.b2chat.services.GitHubService;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService iUserService;
	
    private final GitHubService gitHubService;

    @Autowired
    public UserController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }
	
   @GetMapping("/usuario/github/{username}")
    public ResponseEntity<Object> getUserInfo(@PathVariable String username) {
        return gitHubService.getUserInfo(username);       
    }
	   
	@GetMapping(value = "/usuario")
	public ResponseEntity<Object> get() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<User> list = iUserService.findAll();
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping(value = "/usuario/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		try {
			User data = iUserService.findById(id);
			return new ResponseEntity<Object>(data, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/usuario")
	public ResponseEntity<Object> create(@Valid @RequestBody User request, BindingResult bindingResult) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
		     if (bindingResult.hasErrors()) {
		            // Manejar errores de validación
		            Map<String, Object> errores = new HashMap<>();
		            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
		            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
		        }
		   /*  if (bindingResult.hasErrors()) {
		            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().get(0).getDefaultMessage());
		        }*/
			
			User res = iUserService.save(request);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
			
		} catch (DataIntegrityViolationException e) {
			map.put("message", "El correo electrónico ya se encuentra registrado");
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<Object> update(@RequestBody User request, @PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			User currentPerson = iUserService.findById(id);
			
			if(request.getEmail() != null)
				currentPerson.setEmail(request.getEmail());
			
			if(request.getUsername() != null)
				currentPerson.setUsername(request.getUsername());
			
			User res = iUserService.save(currentPerson);

			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User currentPerson = iUserService.findById(id);
			iUserService.delete(currentPerson);
			map.put("deleted", true);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
