package com.maraphon.maraphonskills.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RegistrationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Registration registration;

    @ManyToOne
    private Event event;

    private Short raceTime;
    private Short totalPlace;
    private Short categoryPlace;
}
