package com.example.inventory_service.controller;

import com.example.inventory_service.dto.InventoryRequestDTO;
import com.example.inventory_service.dto.InventoryResponseDTO;
import com.example.inventory_service.dto.InventoryUpdateDTO;
import com.example.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponseDTO> addInventory(@Valid @RequestBody InventoryRequestDTO dto) {
        return ResponseEntity.ok(inventoryService.addInventory(dto));
    }

    @PutMapping("/stock/{sku}/{warehouseId}")
    public ResponseEntity<InventoryResponseDTO> updateStock(
            @PathVariable String sku,
            @PathVariable Long warehouseId,
            @Valid @RequestBody InventoryUpdateDTO dto) {
        return ResponseEntity.ok(inventoryService.updateStock(sku, warehouseId, dto));
    }

    @GetMapping("/{sku}/{warehouseId}")
    public ResponseEntity<InventoryResponseDTO> getInventory(
            @PathVariable String sku,
            @PathVariable Long warehouseId) {
        return ResponseEntity.ok(inventoryService.getInventory(sku, warehouseId));
    }
}