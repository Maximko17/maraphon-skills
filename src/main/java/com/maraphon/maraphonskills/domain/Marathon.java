package com.maraphon.maraphonskills.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Marathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private Short year;
    private String countryCode;

    @OneToMany(mappedBy = "marathon")
    public Set<Event> events = new HashSet<>();

}
