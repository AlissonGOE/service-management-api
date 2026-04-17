package com.alisson.service_management.service;

import com.alisson.service_management.dto.ClientDTO;
import com.alisson.service_management.mapper.ClientMapper;
import com.alisson.service_management.model.Client;
import com.alisson.service_management.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    // Create client
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.map(clientDTO);
        client = clientRepository.save(client);
        return clientMapper.map(client);
    }

    // List client
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::map)
                .collect(Collectors.toList());
    }

    // List client per Id
    public ClientDTO findById(Long id) {
        Optional<Client> clientPerId = clientRepository.findById(id);
        return clientPerId.map(clientMapper::map).orElse(null);
    }

    // List client per name
    public ClientDTO findByName(String name) {
        Optional<Client> clientPerName = clientRepository.findByName(name);
        return clientPerName.map(clientMapper::map).orElse(null);
    }

    // Update client
    public ClientDTO updateById(Long id, ClientDTO clientDTO) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            Client updatedClient = clientMapper.map(clientDTO);
            updatedClient.setId(id);
            Client saveClient = clientRepository.save(updatedClient);
            return clientMapper.map(saveClient);
        }
        return null;
    }

    // Delete client per Id
    public void deleteClientPerId(Long id) {
        clientRepository.deleteById(id);
    }
}
