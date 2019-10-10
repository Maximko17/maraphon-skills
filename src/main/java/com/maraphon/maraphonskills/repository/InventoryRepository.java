package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Short> {
}
