package com.megamedios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.qos.logback.core.net.server.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{
	Optional<Client> findByCorreo(String correo);
}
