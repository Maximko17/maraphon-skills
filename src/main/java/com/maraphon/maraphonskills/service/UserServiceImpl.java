package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.UserForm;
import com.maraphon.maraphonskills.converters.UserFormToUser;
import com.maraphon.maraphonskills.converters.UserToUserForm;
import com.maraphon.maraphonskills.domain.Authorities;
import com.maraphon.maraphonskills.domain.User;
import com.maraphon.maraphonskills.repository.AuthorityRepository;
import com.maraphon.maraphonskills.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserToUserForm userToUserForm;
    private UserFormToUser userFormToUser;
    private AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, UserToUserForm userToUserForm, UserFormToUser userFormToUser, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.userToUserForm = userToUserForm;
        this.userFormToUser = userFormToUser;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public User saveOrUpdate(UserForm userForm, String authority) {
        User user = userFormToUser.convert(userForm);

        user.getAuthorities().forEach(authorities -> authorityRepository.delete(authorities));
        Authorities newAuthority = new Authorities();

        newAuthority.setAuthority(authority);
        newAuthority.setUser(user);
        Set<Authorities> authorities = new HashSet<>();
        authorities.add(newAuthority);

        user.setAuthorities(authorities);

        userRepository.save(user);

        return user;
    }

    @Override
    public UserForm getUserCommandByEmail(String username) {
        return userToUserForm.convert(userRepository.findByUsername(username));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);

        return userList;
    }

    @Override
    public List<User> sortByRollAndName(Authorities authorities) {
        return userRepository.findByAuthorities(authorities);
    }

}
