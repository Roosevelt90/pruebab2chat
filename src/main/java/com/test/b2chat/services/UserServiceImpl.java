package com.test.b2chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.b2chat.entities.User;
import com.test.b2chat.iservices.IUserService;
import com.test.b2chat.repository.UserDao;
import com.test.b2chat.util.EncryptionComponent;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
	private UserDao userDao;
    
    private final EncryptionComponent encryptionComponent;
    
    public UserServiceImpl(EncryptionComponent encryptionComponent) {
    	this.encryptionComponent = encryptionComponent;
    }
    
	@Override
	@Transactional
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}
	
	@Override
	@Transactional
	public User save(User entidad) {
		entidad.setPassword(encryptionComponent.encrypt(entidad.getPassword()));	
		return userDao.save(entidad);
	}

	@Override 
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(User entidad) {
		userDao.delete(entidad);
		
	}    
}
