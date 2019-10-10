package com.maraphon.maraphonskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "registrations")
@Data
public class RegistrationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationStatus;

    @OneToMany(mappedBy = "registrationStatus")
    public Set<Registration> registrations = new HashSet<>();
}
