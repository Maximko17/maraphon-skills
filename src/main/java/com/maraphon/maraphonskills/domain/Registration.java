package com.maraphon.maraphonskills.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"sponsorShips", "registrationEvent"})
@Data
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    public Runner runner;
    private String registrationDateTime;
    @ManyToOne
    public Charity charity;
    @ManyToOne
    public RaceKitOption raceKitOption;
    @ManyToOne
    public RegistrationStatus registrationStatus;

    private Short sponsorTarget;
    @OneToMany(mappedBy = "registration")
    public Set<SponsorShip> sponsorShips = new HashSet<>();

    @OneToMany(mappedBy = "registration")
    public Set<RegistrationEvent> registrationEvent = new HashSet<>();
}
