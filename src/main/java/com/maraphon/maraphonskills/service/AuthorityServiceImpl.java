package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.Authorities;
import com.maraphon.maraphonskills.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authorities> getAuthorityList() {
        List<Authorities> authorities = new ArrayList<>();
        authorityRepository.findAll().forEach(authorities::add);

        return authorities;
    }

    @Override
    public List<Authorities> findByAuthorityOrderByUser_FirstName(String role) {
        return authorityRepository.findByAuthorityOrderByUser_FirstName(role);
    }

    @Override
    public List<Authorities> findByAuthorityOrderByUser_LastName(String role) {
        return authorityRepository.findByAuthorityOrderByUser_LastName(role);
    }

    @Override
    public List<Authorities> findByAuthorityOrderByUser_Username(String role) {
        return authorityRepository.findByAuthorityOrderByUser_Username(role);
    }

    @Override
    public List<Authorities> findAllByOrderByUser_FirstName() {
        return authorityRepository.findAllByOrderByUser_FirstName();
    }

    @Override
    public List<Authorities> findAllByOrderByUser_LastName() {
        return authorityRepository.findAllByOrderByUser_LastName();
    }

    @Override
    public List<Authorities> findAllByOrderByUser_Username() {
        return authorityRepository.findAllByOrderByUser_Username();
    }


    @Override
    public List<Authorities> findBySearch(String firstName, String lastName, String userName) {
        return authorityRepository.findByUser_UsernameContainingOrUser_FirstNameContainingOrUser_LastNameContaining(firstName, lastName, userName);
    }
}
