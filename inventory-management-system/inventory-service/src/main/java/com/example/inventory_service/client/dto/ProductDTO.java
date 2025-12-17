package com.example.inventory_service.client.dto;


import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String sku;
    private Boolean status;
}