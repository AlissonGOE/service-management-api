package com.alisson.service_management.controller;

import com.alisson.service_management.dto.ItemDTO;
import com.alisson.service_management.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clients/{clientId}")
public class ClientItemController {

    private final ItemService itemService;

    public ClientItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // List item per Id client
    @GetMapping("/items")
    @Operation(summary = "List all items in the client", description = "Route to list all items in the client.")
    public ResponseEntity<List<ItemDTO>> findByClientId(
            @Parameter(description = "The user enter the (Id) in the request body.")
            @PathVariable Long clientId) {
        List<ItemDTO> itemDTOS = itemService.findByClientId(clientId);
        return ResponseEntity.ok(itemDTOS);
    }
}
