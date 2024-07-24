package com.megamedios.service;

import java.util.List;

import com.megamedios.entities.User;

public interface IUserService {
	
	User saveUser(User user);
	List<User> getAllUsers();
	void deleteUser(String Correo);
	User getUserByCorreo(String Correo);

}
