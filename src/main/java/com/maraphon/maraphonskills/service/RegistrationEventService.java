package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.RegistrationEvent;

import java.util.List;

public interface RegistrationEventService {

    RegistrationEvent save(RegistrationEvent registrationEvent);

    List<RegistrationEvent> findAllByRegistration(Registration registration);

    List<RegistrationEvent> findAllByRegistrationAndYear(Registration registration, Short year);

    List<RegistrationEvent> findAllByRegistrationAndYearNot(Registration registration, Short year);

    List<RegistrationEvent> getAllRunnersAndRaceTimeIsNotNull(String idOne, String idTwo);

    List<RegistrationEvent> sortRunners(Short year, String distance, String gender);

    List<RegistrationEvent> findAllByMarathonYear(Short year);

    List<RegistrationEvent> findAllByPaymentStatusAndEventTypeAndYearOrderByFirstName(String status, String marathonType, Short year);

    List<RegistrationEvent> findAllByPaymentStatusAndEventTypeAndYearOrderByLastName(String status, String marathonType, Short year);

    List<RegistrationEvent> findAllByPaymentStatusAndEventTypeAndYearOrderByUsername(String status, String marathonType, Short year);

    Integer totalFinishers(String id, Short year, String gender);

    Integer totalRaceTime(String id, Short year, String gender);

}
