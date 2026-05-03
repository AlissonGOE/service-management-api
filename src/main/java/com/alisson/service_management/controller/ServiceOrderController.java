package com.alisson.service_management.controller;

import com.alisson.service_management.dto.ClientDTO;
import com.alisson.service_management.dto.ServiceOrderDTO;
import com.alisson.service_management.service.ServiceOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients/{clientId}/service-orders")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    public ServiceOrderController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    // Create Service order by client
    @PostMapping()
    @Operation(summary = "Create a new service order.", description = "Route to create a new service order and insert into the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service order created successfully."),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<?> createServiceOrder(
            @Parameter(description = "The user enters the client (Id) in the path.")
            @PathVariable Long clientId,
            @RequestBody ServiceOrderDTO serviceOrderDTO) {
        ServiceOrderDTO newServiceOrder = serviceOrderService.createServiceOrder(clientId, serviceOrderDTO);
        if (newServiceOrder != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newServiceOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The client with (Id): " + clientId + " was not found.");
        }

    }

    // List service order
    @GetMapping
    @Operation(summary = "List all service orders", description = "Route to list all service orders.")
    public ResponseEntity<List<ServiceOrderDTO>> findAll() {
        List<ServiceOrderDTO> serviceOrderDTOS = serviceOrderService.findAll();
        return ResponseEntity.ok(serviceOrderDTOS);
    }

    // List service order per Id
    @GetMapping("/{id}")
    @Operation(summary = "List the service order by (Id)", description = "Routo to list service order by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service order successfully."),
            @ApiResponse(responseCode = "404", description = "Service order not found.")
    })
    public ResponseEntity<?> findById(
            @Parameter(description = "The user enter the (Id) in the request body.")
            @PathVariable Long id) {
        ServiceOrderDTO serviceOrderDTO = serviceOrderService.findById(id);
        if (serviceOrderDTO != null) {
            return ResponseEntity.ok(serviceOrderDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The service order with (Id): " + id + " was not found.");
        }
    }

    // Update service order by Id
    @PutMapping("/{id}")
    @Operation(summary = "Update the service order by (Id)", description = "Route to update the service order by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service order updated successfully"),
            @ApiResponse(responseCode = "404", description = "Service order was not found.")
    })
    public ResponseEntity<?> updateById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id,
            @Parameter(description = "The user enters the (Data) in the request body.")
            @RequestBody ServiceOrderDTO updatedServiceOrder) {
        ServiceOrderDTO serviceOrderDTO = serviceOrderService.updateById(id, updatedServiceOrder);
        if (serviceOrderDTO != null) {
            return ResponseEntity.ok(serviceOrderDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The service order with (Id): "  + id +  " was not found.");
        }
    }

    // Delete service order by Id
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the service order by (Id)", description = "Route to delete the service order by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service order deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Service order was not found.")
    })
    public  ResponseEntity<String> deletById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id) {
        if (serviceOrderService.findById(id) != null) {
            serviceOrderService.deleteById(id);
            return ResponseEntity.ok("The service order with (Id): " + id + " was deletede successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The service order with (Id): "  + id +  " was not found.");
        }
    }
}
