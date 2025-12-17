package com.example.supplier_service.dto;


import lombok.Data;

@Data
public class SupplierResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean active;
}