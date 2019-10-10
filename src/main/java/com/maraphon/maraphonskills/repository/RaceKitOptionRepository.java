package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.RaceKitOption;
import org.springframework.data.repository.CrudRepository;

public interface RaceKitOptionRepository extends CrudRepository<RaceKitOption, String> {
    RaceKitOption findByType(String type);
}
