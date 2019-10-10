package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.RegistrationEvent;
import com.maraphon.maraphonskills.repository.RegistrationEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationEventServiceImpl implements RegistrationEventService {

    private RegistrationEventRepository registrationEventRepository;

    public RegistrationEventServiceImpl(RegistrationEventRepository registrationEventRepository) {
        this.registrationEventRepository = registrationEventRepository;
    }

    @Override
    public RegistrationEvent save(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public List<RegistrationEvent> findAllByRegistration(Registration registration) {
        return registrationEventRepository.findAllByRegistration(registration);
    }

    @Override
    public List<RegistrationEvent> findAllByRegistrationAndYear(Registration registration, Short year) {
        return registrationEventRepository.findAllByRegistrationAndEvent_Marathon_Year(registration, year);
    }

    @Override
    public List<RegistrationEvent> findAllByRegistrationAndYearNot(Registration registration, Short year) {
        return registrationEventRepository.findAllByRegistrationAndEvent_Marathon_YearNot(registration, year);
    }

    @Override
    public List<RegistrationEvent> getAllRunnersAndRaceTimeIsNotNull(String idOne, String idTwo) {
        return registrationEventRepository.findByEvent_IdNotContainingAndEvent_IdContainingAndRaceTimeIsNotNullOrderByRaceTime(idOne, idTwo);
    }

    @Override
    public List<RegistrationEvent> sortRunners(Short year, String distance, String gender) {
        return registrationEventRepository.findByEvent_Marathon_YearAndEvent_IdContainingAndRegistration_Runner_GenderAndRaceTimeIsNotNull(year, distance, gender);
    }

    @Override
    public List<RegistrationEvent> findAllByMarathonYear(Short year) {
        return registrationEventRepository.findAllByEvent_Marathon_Year(year);
    }

    @Override
    public List<RegistrationEvent> findAllByPaymentStatusAndEventTypeAndYearOrderByFirstName(String status, String marathonType, Short year) {
        return registrationEventRepository.findAllByRegistration_Runner_PaymentStatusAndEvent_EventTypeAndEvent_Marathon_YearOrderByRegistration_Runner_Email_FirstName(status, marathonType, year);
    }

    @Override
    public List<RegistrationEvent> findAllByPaymentStatusAndEventTypeAndYearOrderByLastName(String status, String marathonType, Short year) {
        return registrationEventRepository.findAllByRegistration_Runner_PaymentStatusAndEvent_EventTypeAndEvent_Marathon_YearOrderByRegistration_Runner_Email_LastName(status, marathonType, year);
    }

    @Override
    public List<RegistrationEvent> findAllByPaymentStatusAndEventTypeAndYearOrderByUsername(String status, String marathonType, Short year) {
        return registrationEventRepository.findAllByRegistration_Runner_PaymentStatusAndEvent_EventTypeAndEvent_Marathon_YearOrderByRegistration_Runner_Email_Username(status, marathonType, year);
    }

    @Override
    public Integer totalFinishers(String id, Short year, String gender) {
        return registrationEventRepository.totalFinishers(id, year, gender);
    }


    @Override
    public Integer totalRaceTime(String id, Short year, String gender) {
        return registrationEventRepository.totalRaceTime(id, year, gender);
    }
}
