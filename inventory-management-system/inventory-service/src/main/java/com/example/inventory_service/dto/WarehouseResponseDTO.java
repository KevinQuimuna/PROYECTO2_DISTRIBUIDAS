package com.example.inventory_service.dto;


import lombok.Data;

@Data
public class WarehouseResponseDTO {
    private Long id;
    private String name;
    private String location;
}