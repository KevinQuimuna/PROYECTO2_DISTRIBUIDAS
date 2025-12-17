package com.example.inventory_service.mapper;

import com.example.inventory_service.dto.InventoryRequestDTO;
import com.example.inventory_service.dto.InventoryResponseDTO;
import com.example.inventory_service.entity.Inventory;
import com.example.inventory_service.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public Inventory toEntity(InventoryRequestDTO dto, Warehouse warehouse) {
        return Inventory.builder()
                .sku(dto.getSku())
                .quantity(dto.getQuantity())
                .warehouse(warehouse)
                .build();
    }

    public InventoryResponseDTO toDTO(Inventory entity) {
        InventoryResponseDTO dto = new InventoryResponseDTO();
        dto.setId(entity.getId());
        dto.setSku(entity.getSku());
        dto.setQuantity(entity.getQuantity());
        dto.setWarehouseName(entity.getWarehouse().getName());
        return dto;
    }
}