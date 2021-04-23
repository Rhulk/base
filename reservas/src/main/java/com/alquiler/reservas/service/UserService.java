package com.alquiler.reservas.service;

import com.alquiler.reservas.entity.User;

public interface UserService {

	public Iterable getAllUsers();
	public User createUser(User formUser) throws Exception;
	public User getUserById(Long id) throws Exception;
		
}