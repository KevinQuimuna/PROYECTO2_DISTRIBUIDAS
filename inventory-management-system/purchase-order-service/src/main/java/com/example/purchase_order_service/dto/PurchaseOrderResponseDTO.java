package com.example.purchase_order_service.dto;

import com.example.purchase_order_service.entity.OrderStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseOrderResponseDTO {
    private Long id;
    private Long supplierId;
    private Long warehouseId;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String notes;
    private List<PurchaseOrderItemResponseDTO> items;

    @Data
    public static class PurchaseOrderItemResponseDTO {
        private Long productId;
        private String sku;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subTotal;
    }
}