package com.megamedios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megamedios.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	boolean existsByCorreo(String correo);
	void deleteByCorreo(String correo);
	Optional<User> findByCorreo(String correo);
}
