package com.test.b2chat.iservices;

import java.util.List;

import com.test.b2chat.entities.User;



public interface IUserService {
	   
    public List<User> findAll();
    
	public User save(User request);

	public User findById(Long id);

	public void delete(User request);    
}
