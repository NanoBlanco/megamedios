package com.megamedios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.megamedios.entities.User;
import com.megamedios.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	
	private final IUserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String Correo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByCorreo(String Correo) {
		// TODO Auto-generated method stub
		return null;
	}

}
