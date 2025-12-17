package com.example.purchase_order_service.client.dto;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Boolean status;
}