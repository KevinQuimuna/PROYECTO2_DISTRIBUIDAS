package com.example.inventory_service.service.impl;

import com.example.inventory_service.client.ProductClient;
import com.example.inventory_service.client.dto.ProductDTO;
import com.example.inventory_service.dto.InventoryRequestDTO;
import com.example.inventory_service.dto.InventoryResponseDTO;
import com.example.inventory_service.dto.InventoryUpdateDTO;
import com.example.inventory_service.entity.Inventory;
import com.example.inventory_service.entity.Warehouse;
import com.example.inventory_service.exception.InsufficientStockException;
import com.example.inventory_service.exception.InventoryNotFoundException;
import com.example.inventory_service.exception.WarehouseNotFoundException;
import com.example.inventory_service.mapper.InventoryMapper;
import com.example.inventory_service.repository.InventoryRepository;
import com.example.inventory_service.repository.WarehouseRepository;
import com.example.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryMapper inventoryMapper;
    // Ahora sí inyectamos el cliente obligatoriamente
    private final ProductClient productClient;

    @Override
    @Transactional
    public InventoryResponseDTO addInventory(InventoryRequestDTO dto) {
        // 1. Validar Bodega
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new WarehouseNotFoundException("Bodega no encontrada"));

        // 2. Validar que el producto exista en el Catálogo (Product Service)
        // Esto evita tener inventario "húerfano"
        Optional<ProductDTO> product = productClient.getProductBySku(dto.getSku());
        if (product.isEmpty()) {
            throw new InventoryNotFoundException("El SKU " + dto.getSku() + " no existe en el catálogo de productos.");
        }

        // 3. Lógica de inventario
        Optional<Inventory> existing = inventoryRepository.findBySkuAndWarehouseId(dto.getSku(), dto.getWarehouseId());

        Inventory inventory;
        if (existing.isPresent()) {
            inventory = existing.get();
            inventory.setQuantity(inventory.getQuantity() + dto.getQuantity());
        } else {
            inventory = inventoryMapper.toEntity(dto, warehouse);
        }

        return inventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    @Transactional
    public InventoryResponseDTO updateStock(String sku, Long warehouseId, InventoryUpdateDTO dto) {
        Inventory inventory = inventoryRepository.findBySkuAndWarehouseId(sku, warehouseId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado para SKU: " + sku));

        int newQuantity = inventory.getQuantity() + dto.getQuantity();

        if (newQuantity < 0) {
            throw new InsufficientStockException("Stock insuficiente. Disponible: " + inventory.getQuantity());
        }

        inventory.setQuantity(newQuantity);
        return inventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryResponseDTO getInventory(String sku, Long warehouseId) {
        Inventory inventory = inventoryRepository.findBySkuAndWarehouseId(sku, warehouseId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));
        return inventoryMapper.toDTO(inventory);
    }
}