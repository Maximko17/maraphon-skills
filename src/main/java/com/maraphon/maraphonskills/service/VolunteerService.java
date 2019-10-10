package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Volunteer;

import java.util.List;

public interface VolunteerService {

    List<Volunteer> getAllVolunteers();

    List<Volunteer> getAllVolunteersOrderByFirstName();

    List<Volunteer> getAllVolunteersOrderByLastName();

    List<Volunteer> getAllVolunteersOrderByCountry();

    Volunteer getVolunteerById(Long id);

    Iterable<Volunteer> saveAll(Iterable<Volunteer> volunteers);
}
