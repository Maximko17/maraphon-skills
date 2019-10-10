package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.UserForm;
import com.maraphon.maraphonskills.domain.Authorities;
import com.maraphon.maraphonskills.domain.User;

import java.util.List;

public interface UserService {

    User saveOrUpdate(UserForm userForm, String authority);

    UserForm getUserCommandByEmail(String username);

    List<User> getAllUsers();

    List<User> sortByRollAndName(Authorities authorities);

}
