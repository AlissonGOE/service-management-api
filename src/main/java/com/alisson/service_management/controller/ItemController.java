package com.alisson.service_management.controller;

import com.alisson.service_management.dto.ItemDTO;
import com.alisson.service_management.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-orders/{serviceOrderId}/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Create Item by Service Order
    @PostMapping()
    @Operation(summary = "Create a new item in service order", description = "Route to create a new item in service order and insert into the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created successfully."),
            @ApiResponse(responseCode = "404", description = "Service order not found.")
    })
    public ResponseEntity<?> createItem(
            @Parameter(description = "The user enters the service order (Id) in the path.")
            @PathVariable Long serviceOrderId,
            @RequestBody ItemDTO itemDTO) {
        ItemDTO newItem = itemService.createItem(serviceOrderId, itemDTO);
        if (newItem != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The service order with (Id): " + serviceOrderId + " was not found");
        }
    }

    // List item per Id service order
    @GetMapping()
    @Operation(summary = "List all items in the service order", description = "Route to list all items in the service order.")
    public ResponseEntity<List<ItemDTO>> findByServiceId(
            @Parameter(description = "The user enter the (Id) in the request body.")
            @PathVariable Long serviceOrderId) {
        List<ItemDTO> itemDTOS = itemService.findByServiceId(serviceOrderId);
        return ResponseEntity.ok(itemDTOS);
    }

    // List item by Id
    @GetMapping("/{id}")
    @Operation(summary = "List the item order by (Id)", description = "Route to list item by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item successfully."),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    public ResponseEntity<?> findById(
            @Parameter(description = "The user enter the (Id) in the request body.")
            @PathVariable Long id) {
        ItemDTO itemDTO = itemService.findById(id);
        if (itemDTO != null) {
            return ResponseEntity.ok(itemDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The item order with (Id): " + id + " was not found.");
        }
    }

    // Update item by Id
    @PutMapping("/{id}")
    @Operation(summary = "Update the item (Id)", description = "Route to update the item by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item updated successfully."),
            @ApiResponse(responseCode = "404", description = "Item was not found.")
    })
    public ResponseEntity<?> updateById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id,
            @RequestBody ItemDTO updateItem) {
        ItemDTO itemDTO = itemService.updateById(id, updateItem);
        if (itemDTO != null) {
            return ResponseEntity.ok(itemDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The item with (Id): " + id + " was not found.");
        }
    }

    // Delete item by Id
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the item by (Id)", description = "Route to delete the item by (Id).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Item was not found.")
    })
    public ResponseEntity<String> deletById(
            @Parameter(description = "The user enters the (Id) in the request body.")
            @PathVariable Long id) {
        if (itemService.findById(id) != null) {
            itemService.deleteById(id);
            return ResponseEntity.ok("The item with (Id): " + id + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The item with (Id): " + id + " was not found");
        }
    }
}
