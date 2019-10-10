package com.maraphon.maraphonskills.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class SponsorShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sponsorName;
    @ManyToOne
    Registration registration;
    private Short amount;
}
