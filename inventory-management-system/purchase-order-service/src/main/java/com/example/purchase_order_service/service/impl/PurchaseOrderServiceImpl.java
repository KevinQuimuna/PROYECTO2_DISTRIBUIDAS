package com.example.purchase_order_service.service.impl;

import com.example.purchase_order_service.client.InventoryClient;
import com.example.purchase_order_service.client.ProductClient;
import com.example.purchase_order_service.client.SupplierClient;
import com.example.purchase_order_service.client.dto.ProductDTO;
import com.example.purchase_order_service.client.dto.SupplierDTO;
import com.example.purchase_order_service.dto.OrderApprovalDTO;
import com.example.purchase_order_service.dto.OrderReceptionDTO;
import com.example.purchase_order_service.dto.PurchaseOrderRequestDTO;
import com.example.purchase_order_service.dto.PurchaseOrderResponseDTO;
import com.example.purchase_order_service.entity.OrderStatus;
import com.example.purchase_order_service.entity.PurchaseOrder;
import com.example.purchase_order_service.entity.PurchaseOrderItem;
import com.example.purchase_order_service.exception.ExternalServiceException;
import com.example.purchase_order_service.exception.InvalidOrderStatusException;
import com.example.purchase_order_service.exception.PurchaseOrderNotFoundException;
import com.example.purchase_order_service.mapper.PurchaseOrderMapper;
import com.example.purchase_order_service.repository.PurchaseOrderRepository;
import com.example.purchase_order_service.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository orderRepository;
    private final PurchaseOrderMapper orderMapper;
    private final ProductClient productClient;
    private final SupplierClient supplierClient;
    private final InventoryClient inventoryClient;

    @Override
    @Transactional
    public PurchaseOrderResponseDTO createOrder(PurchaseOrderRequestDTO dto) {
        // Validar Proveedor
        SupplierDTO supplier = supplierClient.getSupplierById(dto.getSupplierId())
                .orElseThrow(() -> new ExternalServiceException("Proveedor no encontrado"));
        if (!supplier.getActive()) {
            throw new ExternalServiceException("El proveedor no está activo");
        }

        PurchaseOrder order = PurchaseOrder.builder()
                .supplierId(dto.getSupplierId())
                .warehouseId(dto.getWarehouseId())
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .notes(dto.getNotes())
                .items(new ArrayList<>())
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (var itemDTO : dto.getItems()) {
            ProductDTO product = productClient.getProductById(itemDTO.getProductId())
                    .orElseThrow(() -> new ExternalServiceException("Producto no encontrado: " + itemDTO.getProductId()));

            PurchaseOrderItem item = PurchaseOrderItem.builder()
                    .productId(product.getId())
                    .sku(product.getSku())
                    .quantity(itemDTO.getQuantity())
                    .unitPrice(product.getPrice())
                    .purchaseOrder(order)
                    .build();

            order.getItems().add(item);
            totalAmount = totalAmount.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        order.setTotalAmount(totalAmount);
        PurchaseOrder savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderResponseDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new PurchaseOrderNotFoundException("Orden no encontrada: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PurchaseOrderResponseDTO updateOrderStatus(Long id, OrderApprovalDTO approvalDTO) {
        PurchaseOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new PurchaseOrderNotFoundException("Orden no encontrada"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStatusException("Solo se pueden aprobar/cancelar órdenes PENDING");
        }

        order.setStatus(approvalDTO.getStatus());
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    @Transactional
    public PurchaseOrderResponseDTO receiveOrder(Long id, OrderReceptionDTO receptionDTO) {
        PurchaseOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new PurchaseOrderNotFoundException("Orden no encontrada"));

        if (order.getStatus() != OrderStatus.APPROVED) {
            throw new InvalidOrderStatusException("La orden debe estar APROBADA para ser recibida");
        }

        try {
            for (PurchaseOrderItem item : order.getItems()) {
                inventoryClient.updateStock(item.getSku(), order.getWarehouseId(), item.getQuantity());
            }
        } catch (Exception e) {
            throw new ExternalServiceException("Error actualizando inventario: " + e.getMessage());
        }

        order.setStatus(OrderStatus.RECEIVED);
        return orderMapper.toDTO(orderRepository.save(order));
    }
}