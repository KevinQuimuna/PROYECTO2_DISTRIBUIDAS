package com.example.inventory_service.mapper;

import com.example.inventory_service.dto.WarehouseRequestDTO;
import com.example.inventory_service.dto.WarehouseResponseDTO;
import com.example.inventory_service.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {
    public Warehouse toEntity(WarehouseRequestDTO dto) {
        return Warehouse.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }

    public WarehouseResponseDTO toDTO(Warehouse entity) {
        WarehouseResponseDTO dto = new WarehouseResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        return dto;
    }
}