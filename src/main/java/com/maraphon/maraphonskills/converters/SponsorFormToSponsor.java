package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.SponsorShipForm;
import com.maraphon.maraphonskills.domain.SponsorShip;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SponsorFormToSponsor implements Converter<SponsorShipForm, SponsorShip> {

    @Override
    public SponsorShip convert(SponsorShipForm sponsorShipForm) {
        SponsorShip sponsorShip = new SponsorShip();

        sponsorShip.setId(sponsorShipForm.getId());
        sponsorShip.setSponsorName(sponsorShipForm.getSponsorName());
        sponsorShip.setAmount(sponsorShipForm.getAmount());
        sponsorShip.setRegistration(sponsorShipForm.getRegistration());

        return sponsorShip;
    }
}
