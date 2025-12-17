package com.example.purchase_order_service.dto;


import com.example.purchase_order_service.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderApprovalDTO {
    @NotNull
    private OrderStatus status; // APPROVED o CANCELLED
}