package com.example.purchase_order_service.client.dto;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long id;
    private String name;
    private Boolean active;
}