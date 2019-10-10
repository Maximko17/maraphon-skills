package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Inventory;
import com.maraphon.maraphonskills.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory findById(Short id) {
        return inventoryRepository.findById(id).get();
    }

    @Override
    public Inventory save(Inventory newInventory) {
        Inventory inventory = findById((short) 1);

        inventory.setNumber((short) (inventory.getNumber() + newInventory.getNumber()));
        inventory.setRfid((short) (inventory.getRfid() + newInventory.getRfid()));
        inventory.setBaseball((short) (inventory.getBaseball() + newInventory.getBaseball()));
        inventory.setBottleWater((short) (inventory.getBottleWater() + newInventory.getBottleWater()));
        inventory.setTShirt((short) (inventory.getTShirt() + newInventory.getTShirt()));
        inventory.setSuvBooklet((short) (inventory.getSuvBooklet() + newInventory.getSuvBooklet()));

        inventoryRepository.save(inventory);

        return inventory;
    }
}
