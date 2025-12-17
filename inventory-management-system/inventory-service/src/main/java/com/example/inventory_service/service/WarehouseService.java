package com.example.inventory_service.service;


import com.example.inventory_service.dto.WarehouseRequestDTO;
import com.example.inventory_service.dto.WarehouseResponseDTO;
import java.util.List;

public interface WarehouseService {
    WarehouseResponseDTO createWarehouse(WarehouseRequestDTO dto);
    List<WarehouseResponseDTO> getAllWarehouses();
    WarehouseResponseDTO getWarehouseById(Long id);
}