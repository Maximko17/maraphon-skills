package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.CountryCode;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryCode, String> {
}
