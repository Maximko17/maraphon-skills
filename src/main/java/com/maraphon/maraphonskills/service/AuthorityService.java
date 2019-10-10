package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Authorities;

import java.util.List;

public interface AuthorityService {

    List<Authorities> getAuthorityList();

    List<Authorities> findByAuthorityOrderByUser_FirstName(String role);

    List<Authorities> findByAuthorityOrderByUser_LastName(String role);

    List<Authorities> findByAuthorityOrderByUser_Username(String role);

    List<Authorities> findAllByOrderByUser_FirstName();

    List<Authorities> findAllByOrderByUser_LastName();

    List<Authorities> findAllByOrderByUser_Username();

    List<Authorities> findBySearch(String firstName, String lastName, String userName);

}
