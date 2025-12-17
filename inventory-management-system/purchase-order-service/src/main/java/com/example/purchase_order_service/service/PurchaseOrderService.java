package com.example.purchase_order_service.service;

import com.example.purchase_order_service.dto.OrderApprovalDTO;
import com.example.purchase_order_service.dto.OrderReceptionDTO;
import com.example.purchase_order_service.dto.PurchaseOrderRequestDTO;
import com.example.purchase_order_service.dto.PurchaseOrderResponseDTO;
import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderResponseDTO createOrder(PurchaseOrderRequestDTO dto);
    PurchaseOrderResponseDTO getOrderById(Long id);
    List<PurchaseOrderResponseDTO> getAllOrders();
    PurchaseOrderResponseDTO updateOrderStatus(Long id, OrderApprovalDTO approvalDTO);
    PurchaseOrderResponseDTO receiveOrder(Long id, OrderReceptionDTO receptionDTO);
}