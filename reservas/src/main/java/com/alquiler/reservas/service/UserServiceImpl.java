package com.alquiler.reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public Iterable getAllUsers(){
		return userRepository.findAll();
	}
	
}
