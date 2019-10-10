package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Inventory;

public interface InventoryService {

    Inventory findById(Short id);

    Inventory save(Inventory inventory);
}
