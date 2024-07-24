package com.megamedios.service;

import java.util.List;

import com.megamedios.entities.Client;

public interface IClientService {
	
	Client saveClient(Client client);
	List<Client> getAllClients();
	void deleteClient(String correo);
	Client getClientByCorreo(String correo);

}
