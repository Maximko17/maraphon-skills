package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Volunteer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    List<Volunteer> findAllByOrderByFirstName();

    List<Volunteer> findAllByOrderByLastName();

    List<Volunteer> findAllByOrderByCountryCode();
}
