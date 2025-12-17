package com.example.purchase_order_service.client;


import com.example.purchase_order_service.client.dto.SupplierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SupplierClient {
    private final RestTemplate restTemplate;

    @Value("${application.clients.supplier-service-url}")
    private String supplierServiceUrl;

    public Optional<SupplierDTO> getSupplierById(Long id) {
        try {
            return Optional.ofNullable(
                    restTemplate.getForObject(supplierServiceUrl + "/api/suppliers/" + id, SupplierDTO.class)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}