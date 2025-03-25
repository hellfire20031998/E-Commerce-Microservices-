package com.hellFire.Inventory_Service.controller;


import com.hellFire.Inventory_Service.dto.InventoryResponse;
import com.hellFire.Inventory_Service.model.Inventory;
import com.hellFire.Inventory_Service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes) {

        return  inventoryService.isInStock(skuCodes);
    }
    @GetMapping("/getAll")
    public List<Inventory> allInventory() {
        return inventoryService.getAll();
    }
}
