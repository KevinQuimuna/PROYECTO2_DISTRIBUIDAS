package com.example.purchase_order_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderReceptionDTO {
    private LocalDateTime receptionDate;
    private String comments;
    // Podría usarse para recibir parcialmente en el futuro
}