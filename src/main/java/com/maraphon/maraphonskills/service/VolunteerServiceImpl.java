package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Volunteer;
import com.maraphon.maraphonskills.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteerRepository.findAll().forEach(volunteers::add);

        return volunteers;
    }

    @Override
    public List<Volunteer> getAllVolunteersOrderByFirstName() {
        return volunteerRepository.findAllByOrderByFirstName();
    }

    @Override
    public List<Volunteer> getAllVolunteersOrderByLastName() {
        return volunteerRepository.findAllByOrderByLastName();
    }

    @Override
    public List<Volunteer> getAllVolunteersOrderByCountry() {
        return volunteerRepository.findAllByOrderByCountryCode();
    }

    @Override
    public Volunteer getVolunteerById(Long id) {
        return null;
    }

    @Override
    public Iterable<Volunteer> saveAll(Iterable<Volunteer> volunteers) {
        return volunteerRepository.saveAll(volunteers);
    }


}
