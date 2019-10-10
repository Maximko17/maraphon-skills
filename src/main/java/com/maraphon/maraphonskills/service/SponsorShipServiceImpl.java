package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.SponsorShipForm;
import com.maraphon.maraphonskills.converters.SponsorFormToSponsor;
import com.maraphon.maraphonskills.domain.SponsorShip;
import com.maraphon.maraphonskills.repository.SponsorShipRepository;
import org.springframework.stereotype.Service;

@Service
public class SponsorShipServiceImpl implements SponsorShipService {

    private SponsorShipRepository sponsorShipRepository;

    public SponsorShipServiceImpl(SponsorShipRepository sponsorShipRepository) {
        this.sponsorShipRepository = sponsorShipRepository;
    }

    @Override
    public SponsorShip save(SponsorShip sponsorShip) {

        sponsorShipRepository.save(sponsorShip);

        return sponsorShip;
    }
}
