package com.maraphon.maraphonskills.repository;

import com.maraphon.maraphonskills.domain.Authorities;
import com.maraphon.maraphonskills.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u"
            + " left join fetch u.authorities"
            + " where u.username = :username")
    User findByUsername(@Param("username") String email);

    List<User> findByAuthorities(Authorities authorities);

}
