package com.alisson.service_management.controller;

import com.alisson.service_management.dto.ClientDTO;
import com.alisson.service_management.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create
    @PostMapping
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

    // Read
    @GetMapping
    @Operation(summary = "List all clients", description = "Route to list all clients")
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    // Read by Id
    @GetMapping("/{id}")
    @Operation(summary = "List the client by (Id)", description = "Route to list the client by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found successfully."),
            @ApiResponse(responseCode = "404", description = "Client was not found.")
    })
    public ResponseEntity<?> findById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id) {
        ClientDTO client = clientService.findById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The client with (Id): " + id + " was not found.");
        }

    }

    // Read by Name
    @GetMapping("/name/{name}")
    @Operation(summary = "List the client by (Name)", description = "Route to list the client by (Name).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found successfully."),
            @ApiResponse(responseCode = "404", description = "Client was not found.")
    })
    public ResponseEntity<?> findByName(
            @Parameter(description = "The user enters the (Name) in the request body.")
            @PathVariable String name) {
        ClientDTO client = clientService.findByName(name);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The client with (Name): " + name + " was not found.");
        }

    }

    // Update by Id
    @PutMapping("/{id}")
    @Operation(summary = "Update the client by (Id)", description = "Route to update the client by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client was not found.")
    })
    public ResponseEntity<?> updateById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id,
            @Parameter(description = "The user enters the (Data) in the request body.")
            @RequestBody ClientDTO updatedClient) {
        ClientDTO client = clientService.updateById(id, updatedClient);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The client with (Id): "  + id +  " was not found.");
        }
    }

    // Delete by Id
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the client by (Id)", description = "Route to delete the client by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Client was not found.")
    })
    public  ResponseEntity<String> deletById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id) {
        if (clientService.findById(id) != null) {
            clientService.deleteClientPerId(id);
            return ResponseEntity.ok("The client with (Id): " + id + " was deletede successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The client with (Id): "  + id +  " was not found.");
        }
    }

}
