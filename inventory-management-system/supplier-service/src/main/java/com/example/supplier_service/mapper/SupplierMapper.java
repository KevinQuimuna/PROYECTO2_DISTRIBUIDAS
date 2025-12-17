package com.example.supplier_service.mapper;

import com.example.supplier_service.dto.SupplierRequestDTO;
import com.example.supplier_service.dto.SupplierResponseDTO;
import com.example.supplier_service.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequestDTO dto) {
        return Supplier.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .active(true)
                .build();
    }

    public SupplierResponseDTO toDTO(Supplier entity) {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setActive(entity.getActive());
        return dto;
    }

    public void updateEntity(Supplier entity, SupplierRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        // No actualizamos email si es la clave única de negocio, o requiere validación extra
    }
}