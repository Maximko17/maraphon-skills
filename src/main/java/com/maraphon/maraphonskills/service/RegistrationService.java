package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.Runner;

import java.util.List;

public interface RegistrationService {

    List<Registration> registrationList();

    Registration save(Registration registration);

    Registration findByRunner(Runner runner);

    List<Registration> findAllByKitype(String kit);

}
