package com.maraphon.maraphonskills.commands;

import com.maraphon.maraphonskills.domain.Registration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorShipForm {

    private Long id;

    private String sponsorName;

    public Registration registration;
    private Short amount;
}
