package com.test.b2chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.b2chat.entities.User;


public interface UserDao extends JpaRepository<User, Long>{

}
