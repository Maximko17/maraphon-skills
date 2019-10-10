package com.maraphon.maraphonskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "registrations")
@Data
public class RaceKitOption {

    @Id
    private String type;

    private String raceKit;
    private Short cost;

    @OneToMany(mappedBy = "raceKitOption")
    public Set<Registration> registrations = new HashSet<>();

}
