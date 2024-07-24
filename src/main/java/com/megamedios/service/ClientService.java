package com.megamedios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.megamedios.entities.Client;
import com.megamedios.repositories.IClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
	
	private final IClientRepository clientRepository;

	@Override
	public Client saveClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClient(String correo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client getClientByCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

}
