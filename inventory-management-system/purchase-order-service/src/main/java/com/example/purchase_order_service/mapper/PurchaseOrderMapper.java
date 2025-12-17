package com.example.purchase_order_service.mapper;

import com.example.purchase_order_service.dto.PurchaseOrderResponseDTO;
import com.example.purchase_order_service.entity.PurchaseOrder;
import com.example.purchase_order_service.entity.PurchaseOrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class PurchaseOrderMapper {

    public PurchaseOrderResponseDTO toDTO(PurchaseOrder entity) {
        PurchaseOrderResponseDTO dto = new PurchaseOrderResponseDTO();
        dto.setId(entity.getId());
        dto.setSupplierId(entity.getSupplierId());
        dto.setWarehouseId(entity.getWarehouseId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setStatus(entity.getStatus());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setNotes(entity.getNotes());

        if (entity.getItems() != null) {
            dto.setItems(entity.getItems().stream().map(this::toItemDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    private PurchaseOrderResponseDTO.PurchaseOrderItemResponseDTO toItemDTO(PurchaseOrderItem item) {
        PurchaseOrderResponseDTO.PurchaseOrderItemResponseDTO dto = new PurchaseOrderResponseDTO.PurchaseOrderItemResponseDTO();
        dto.setProductId(item.getProductId());
        dto.setSku(item.getSku());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setSubTotal(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        return dto;
    }
}