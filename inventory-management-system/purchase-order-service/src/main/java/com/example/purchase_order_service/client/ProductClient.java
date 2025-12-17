package com.example.purchase_order_service.client;
import com.example.purchase_order_service.client.dto.ProductDTO;
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

    public Optional<ProductDTO> getProductById(Long id) {
        try {
            return Optional.ofNullable(
                    restTemplate.getForObject(productServiceUrl + "/api/products/" + id, ProductDTO.class)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}