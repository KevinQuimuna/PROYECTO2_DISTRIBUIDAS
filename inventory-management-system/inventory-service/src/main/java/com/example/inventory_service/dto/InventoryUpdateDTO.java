package com.example.inventory_service.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryUpdateDTO {
    @NotNull(message = "La cantidad a ajustar es obligatoria")
    private Integer quantity; // Puede ser positivo (entrada) o negativo (salida)
}