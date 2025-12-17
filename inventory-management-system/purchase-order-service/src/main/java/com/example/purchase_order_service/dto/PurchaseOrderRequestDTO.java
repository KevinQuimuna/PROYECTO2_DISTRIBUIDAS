package com.example.purchase_order_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class PurchaseOrderRequestDTO {
    @NotNull(message = "El proveedor es obligatorio")
    private Long supplierId;

    @NotNull(message = "La bodega de destino es obligatoria")
    private Long warehouseId;

    private String notes;

    @NotEmpty(message = "La orden debe tener al menos un producto")
    private List<PurchaseOrderItemDTO> items;
}