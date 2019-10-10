package com.maraphon.maraphonskills.security;

import com.maraphon.maraphonskills.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomSecurityUser extends User implements UserDetails {
    private static final long serialVersionUID = -4381938875186527688L;

    public CustomSecurityUser() {
    }

    public CustomSecurityUser(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPassword("{noop}" + user.getPassword());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}