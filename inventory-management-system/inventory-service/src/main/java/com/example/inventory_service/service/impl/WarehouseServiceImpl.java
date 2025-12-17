package com.example.inventory_service.service.impl;


import com.example.inventory_service.dto.WarehouseRequestDTO;
import com.example.inventory_service.dto.WarehouseResponseDTO;
import com.example.inventory_service.entity.Warehouse;
import com.example.inventory_service.exception.WarehouseNotFoundException;
import com.example.inventory_service.mapper.WarehouseMapper;
import com.example.inventory_service.repository.WarehouseRepository;
import com.example.inventory_service.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO dto) {
        Warehouse warehouse = warehouseMapper.toEntity(dto);
        return warehouseMapper.toDTO(warehouseRepository.save(warehouse));
    }

    @Override
    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseResponseDTO getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .map(warehouseMapper::toDTO)
                .orElseThrow(() -> new WarehouseNotFoundException("Bodega no encontrada con ID: " + id));
    }
}