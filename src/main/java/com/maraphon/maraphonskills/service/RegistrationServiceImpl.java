package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.Runner;
import com.maraphon.maraphonskills.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<Registration> registrationList() {
        List<Registration> registrations = new ArrayList<>();
        registrationRepository.findAll().forEach(registrations::add);

        return registrations;
    }

    @Override
    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public Registration findByRunner(Runner runner) {
        return registrationRepository.findByRunner(runner);
    }

    @Override
    public List<Registration> findAllByKitype(String kit) {
        return registrationRepository.findAllByRaceKitOption_Type(kit);
    }
}
