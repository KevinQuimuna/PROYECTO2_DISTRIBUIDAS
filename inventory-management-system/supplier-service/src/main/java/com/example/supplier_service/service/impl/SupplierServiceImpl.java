package com.example.supplier_service.service.impl;


import com.example.supplier_service.dto.SupplierRequestDTO;
import com.example.supplier_service.dto.SupplierResponseDTO;
import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.exception.DuplicateSupplierException;
import com.example.supplier_service.exception.SupplierNotFoundException;
import com.example.supplier_service.mapper.SupplierMapper;
import com.example.supplier_service.repository.SupplierRepository;
import com.example.supplier_service.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    @Transactional
    public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO) {
        if (supplierRepository.existsByEmail(supplierRequestDTO.getEmail())) {
            throw new DuplicateSupplierException("El proveedor con email " + supplierRequestDTO.getEmail() + " ya existe.");
        }
        Supplier supplier = supplierMapper.toEntity(supplierRequestDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDTO(savedSupplier);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Proveedor no encontrado con ID: " + id));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO supplierRequestDTO) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Proveedor no encontrado con ID: " + id));

        supplierMapper.updateEntity(supplier, supplierRequestDTO);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDTO(updatedSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new SupplierNotFoundException("Proveedor no encontrado con ID: " + id);
        }
        supplierRepository.deleteById(id);
    }
}