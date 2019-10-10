package com.maraphon.maraphonskills.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Authorities implements GrantedAuthority {
    private static final long serialVersionUID = -8123526131047887755L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToOne
    private User user;

    @Override
    public String getAuthority() {
        return authority;
    }
}
