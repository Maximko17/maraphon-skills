package com.maraphon.maraphonskills.commands;

import com.maraphon.maraphonskills.domain.Authorities;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserForm {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private Set<Authorities> authorities = new HashSet<>();
}
