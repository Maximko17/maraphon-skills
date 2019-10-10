package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.UserForm;
import com.maraphon.maraphonskills.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserForm implements Converter<User, UserForm> {
    @Override
    public UserForm convert(User user) {
        UserForm userForm = new UserForm();

        userForm.setUsername(user.getUsername());
        userForm.setPassword(user.getPassword());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());

        user.getAuthorities().forEach(authorities -> userForm.getAuthorities().add(authorities));

        return userForm;
    }
}
