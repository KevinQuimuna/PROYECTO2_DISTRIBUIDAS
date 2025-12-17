package com.example.inventory_service.client;

import com.example.inventory_service.client.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;

    @Value("${application.clients.product-service-url}")
    private String productServiceUrl;

    public Optional<ProductDTO> getProductBySku(String sku) {
        try {
            // Llama al endpoint que acabamos de crear en ProductService
            ProductDTO product = restTemplate.getForObject(
                    productServiceUrl + "/api/products/sku/" + sku,
                    ProductDTO.class
            );
            return Optional.ofNullable(product);
        } catch (Exception e) {
            // Si el producto no existe o el servicio falla, devolvemos empty
            return Optional.empty();
        }
    }

    public boolean checkProductExists(Long productId) {
        try {
            restTemplate.getForEntity(productServiceUrl + "/api/products/" + productId, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}