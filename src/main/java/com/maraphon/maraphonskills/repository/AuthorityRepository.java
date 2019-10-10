package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Authorities;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authorities, Long> {

    List<Authorities> findByAuthorityOrderByUser_FirstName(String role);

    List<Authorities> findByAuthorityOrderByUser_LastName(String role);

    List<Authorities> findByAuthorityOrderByUser_Username(String role);

    List<Authorities> findAllByOrderByUser_FirstName();

    List<Authorities> findAllByOrderByUser_LastName();

    List<Authorities> findAllByOrderByUser_Username();

    List<Authorities> findByUser_UsernameContainingOrUser_FirstNameContainingOrUser_LastNameContaining(String firstName, String lastName, String userName);


}
