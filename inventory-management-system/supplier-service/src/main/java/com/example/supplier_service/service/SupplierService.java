package com.example.supplier_service.service;


import com.example.supplier_service.dto.SupplierRequestDTO;
import com.example.supplier_service.dto.SupplierResponseDTO;
import java.util.List;

public interface SupplierService {
    SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO);
    SupplierResponseDTO getSupplierById(Long id);
    List<SupplierResponseDTO> getAllSuppliers();
    SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO supplierRequestDTO);
    void deleteSupplier(Long id);
}