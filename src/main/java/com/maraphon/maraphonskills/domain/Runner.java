package com.maraphon.maraphonskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "registrations")
@Data
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String dateOfBirth;
    private String filename;

    @OneToOne(cascade = CascadeType.ALL)
    private User email;

    private String countryCode;

    @OneToMany(mappedBy = "runner")
    public Set<Registration> registrations = new HashSet<>();

    private String paymentStatus;


}
