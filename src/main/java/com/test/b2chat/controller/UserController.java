package com.test.b2chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	@GetMapping(value = "/hola")
	public ResponseEntity<Object> hola() {
		return new ResponseEntity<Object>("Hola", HttpStatus.OK);

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
	public ResponseEntity<Object> create(@RequestBody User request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User res = iUserService.save(request);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
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

			/*
			 * currentPerson.setId(currentPerson.getId());
			 * currentPerson.setNitProveedor(person.getNitProveedor());
			 */

			/*
			 * currentPerson.setDescripcion(person.getDescripcion());
			 * currentPerson.setCantidad(person.getCantidad());
			 * currentPerson.setPrecio(person.getPrecio());
			 */

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
