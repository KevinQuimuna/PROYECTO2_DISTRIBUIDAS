package com.example.purchase_order_service.client;

import com.example.purchase_order_service.client.dto.InventoryUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class InventoryClient {
    private final RestTemplate restTemplate;

    @Value("${application.clients.inventory-service-url}")
    private String inventoryServiceUrl;

    public void updateStock(String sku, Long warehouseId, Integer quantity) {
        String url = inventoryServiceUrl + "/api/inventory/stock/" + sku + "/" + warehouseId;
        InventoryUpdateDTO updateDTO = new InventoryUpdateDTO(quantity);
        restTemplate.put(url, updateDTO);
    }
}