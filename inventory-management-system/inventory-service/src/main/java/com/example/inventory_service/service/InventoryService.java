package com.example.inventory_service.service;

import com.example.inventory_service.dto.InventoryRequestDTO;
import com.example.inventory_service.dto.InventoryResponseDTO;
import com.example.inventory_service.dto.InventoryUpdateDTO;

public interface InventoryService {
    InventoryResponseDTO addInventory(InventoryRequestDTO dto);
    InventoryResponseDTO updateStock(String sku, Long warehouseId, InventoryUpdateDTO dto);
    InventoryResponseDTO getInventory(String sku, Long warehouseId);
}