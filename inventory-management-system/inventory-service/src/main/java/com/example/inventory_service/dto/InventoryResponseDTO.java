package com.example.inventory_service.dto;

import lombok.Data;

@Data
public class InventoryResponseDTO {
    private Long id;
    private String sku;
    private Integer quantity;
    private String warehouseName;
}