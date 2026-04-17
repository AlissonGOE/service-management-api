package com.alisson.service_management.controller;

import com.alisson.service_management.dto.ClientDTO;
import com.alisson.service_management.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create
    @PostMapping("/create")
    @Operation(summary = "Create a new customer.", description = "Route to create a new client and insert it into the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully."),
            @ApiResponse(responseCode = "400", description = "Error creating new client.")
    })
    public ResponseEntity<String> createClient(@RequestBody ClientDTO client) {
        ClientDTO newClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Client created successfully " + newClient.getName() + " (Id): " + newClient.getId());
    }

}
