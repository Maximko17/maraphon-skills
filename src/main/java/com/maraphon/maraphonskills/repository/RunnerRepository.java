package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Runner;
import com.maraphon.maraphonskills.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface RunnerRepository extends CrudRepository<Runner, Long> {

    Runner findByEmail(User email);
}
