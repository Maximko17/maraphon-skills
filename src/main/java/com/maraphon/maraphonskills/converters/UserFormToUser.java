package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.UserForm;
import com.maraphon.maraphonskills.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.springframework.util.ObjectUtils.isEmpty;


@Component
public class UserFormToUser implements Converter<UserForm, User> {

    @Override
    public User convert(UserForm userForm) {
        User user = new User();

        user.setUsername(userForm.getUsername());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());

        return user;
    }
}
