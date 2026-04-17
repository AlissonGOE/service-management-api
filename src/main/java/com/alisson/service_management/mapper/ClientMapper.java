package com.alisson.service_management.mapper;

import com.alisson.service_management.dto.ClientDTO;
import com.alisson.service_management.model.Client;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private final ServiceOrderMapper serviceOrderMapper = new ServiceOrderMapper();

    public Client map(ClientDTO clientDTO) {

        Client client = new Client();

        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setPhone(clientDTO.getPhone());

        if (clientDTO.getServices() != null) {
            client.setServices(
                    clientDTO.getServices().stream()
                            .map(serviceOrderMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return client;
    }

    public ClientDTO map(Client client) {

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setPhone(client.getPhone());

        if (client.getServices() != null) {
            clientDTO.setServices(
                    client.getServices().stream()
                            .map(serviceOrderMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return clientDTO;
    }

}
