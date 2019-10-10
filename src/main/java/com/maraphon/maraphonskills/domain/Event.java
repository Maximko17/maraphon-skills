package com.maraphon.maraphonskills.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Event {

    @Id
    private String id;

    private String eventName;

    public String eventType;

    @ManyToOne
    public Marathon marathon;

    private String startDate;

    private Short cost;

    private Short maxParticipants;

    @OneToMany(mappedBy = "event")
    public Set<RegistrationEvent> registrationEvents = new HashSet<>();
}
