package com.example.purchase_order_service.controller;

import com.example.purchase_order_service.dto.OrderApprovalDTO;
import com.example.purchase_order_service.dto.OrderReceptionDTO;
import com.example.purchase_order_service.dto.PurchaseOrderRequestDTO;
import com.example.purchase_order_service.dto.PurchaseOrderResponseDTO;
import com.example.purchase_order_service.service.PurchaseOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService orderService;

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> createOrder(@Valid @RequestBody PurchaseOrderRequestDTO dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderResponseDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PurchaseOrderResponseDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderApprovalDTO dto) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, dto));
    }

    @PostMapping("/{id}/receive")
    public ResponseEntity<PurchaseOrderResponseDTO> receiveOrder(
            @PathVariable Long id,
            @RequestBody(required = false) OrderReceptionDTO receptionDTO) {
        return ResponseEntity.ok(orderService.receiveOrder(id, receptionDTO));
    }
}