package com.hellFire.Inventory_Service.service;

import com.hellFire.Inventory_Service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
      return inventoryRepository.findBySkuCode(skuCode).isEmpty();
    }
}
