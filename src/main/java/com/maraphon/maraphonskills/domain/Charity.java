package com.maraphon.maraphonskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "registrations")
@Data
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;
    private String fileName;
    private Short sponsorSum;

    @OneToMany(mappedBy = "charity")
    public Set<Registration> registrations = new HashSet<>();
}
