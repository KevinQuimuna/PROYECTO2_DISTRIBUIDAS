package com.example.inventory_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseRequestDTO {
    @NotBlank(message = "El nombre de la bodega es obligatorio")
    private String name;
    private String location;
}