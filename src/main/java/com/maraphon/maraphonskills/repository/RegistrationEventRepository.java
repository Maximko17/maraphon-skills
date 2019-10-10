package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.RegistrationEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationEventRepository extends CrudRepository<RegistrationEvent, Long> {

    List<RegistrationEvent> findAllByRegistration(Registration registration);

    List<RegistrationEvent> findAllByRegistrationAndEvent_Marathon_Year(Registration registration, Short year);

    List<RegistrationEvent> findAllByRegistrationAndEvent_Marathon_YearNot(Registration registration, Short year);

    List<RegistrationEvent> findByEvent_IdNotContainingAndEvent_IdContainingAndRaceTimeIsNotNullOrderByRaceTime(String idOne, String idTwo);

    List<RegistrationEvent> findByEvent_Marathon_YearAndEvent_IdContainingAndRegistration_Runner_GenderAndRaceTimeIsNotNull(Short year, String distance, String gender);

    List<RegistrationEvent> findAllByEvent_Marathon_Year(Short year);

    List<RegistrationEvent> findAllByRegistration_Runner_PaymentStatusAndEvent_EventTypeAndEvent_Marathon_YearOrderByRegistration_Runner_Email_FirstName(String status, String marathonType, Short year);

    List<RegistrationEvent> findAllByRegistration_Runner_PaymentStatusAndEvent_EventTypeAndEvent_Marathon_YearOrderByRegistration_Runner_Email_LastName(String status, String marathonType, Short year);

    List<RegistrationEvent> findAllByRegistration_Runner_PaymentStatusAndEvent_EventTypeAndEvent_Marathon_YearOrderByRegistration_Runner_Email_Username(String status, String marathonType, Short year);

    @Query("select count(re)" +
            "from RegistrationEvent re " +
            "inner join Registration r on re.registration = r.id" +
            " inner join Runner run on r.runner = run.id" +
            " inner join Event e on re.event = e.id " +
            "inner join Marathon m on e.marathon=m.id " +
            " where re.raceTime is not null and e.id like CONCAT('%',:id) and m.year = :year and run.gender like :gender ")
    Integer totalFinishers(@Param("id") String id, @Param("year") Short year, @Param("gender") String gender);

    @Query("select avg(re.raceTime) " +
            " from RegistrationEvent re " +
            "inner join Registration r on re.registration = r.id" +
            " inner join Runner run on r.runner = run.id" +
            " inner join Event e on re.event = e.id" +
            " inner join Marathon m on e.marathon=m.id " +
            " where e.id like CONCAT('%',:id) and m.year = :year and run.gender like :gender")
    Integer totalRaceTime(@Param("id") String id, @Param("year") Short year, @Param("gender") String gender);


}
