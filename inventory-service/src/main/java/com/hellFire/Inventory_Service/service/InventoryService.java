package com.hellFire.Inventory_Service.service;

import com.hellFire.Inventory_Service.dto.InventoryResponse;
import com.hellFire.Inventory_Service.model.Inventory;
import com.hellFire.Inventory_Service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) {

     return  inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }

    public List<Inventory> getAll(){
        return  inventoryRepository.findAll();
    }
}
