package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.Runner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends CrudRepository<Registration, Long> {

    Registration findByRunner(Runner runner);

    @Query("select sum(s.amount) from SponsorShip s where s.registration =:id ")
    List<Long> totalSponsorSum(@Param("id") Registration registrationId);

    List<Registration> findAllByRaceKitOption_Type(String kit);
}
