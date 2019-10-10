package com.maraphon.maraphonskills.controllers;

import com.maraphon.maraphonskills.commands.SponsorShipForm;
import com.maraphon.maraphonskills.domain.Registration;
import com.maraphon.maraphonskills.domain.SponsorShip;
import com.maraphon.maraphonskills.service.RegistrationService;
import com.maraphon.maraphonskills.service.SponsorShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class SponsorController {

    private CountDown countDown = new CountDown();
    private RegistrationService registrationService;
    private SponsorShipService sponsorShipService;

    public SponsorController(RegistrationService registrationService, SponsorShipService sponsorShipService) {
        this.registrationService = registrationService;
        this.sponsorShipService = sponsorShipService;
    }

    @GetMapping("/becomeSponsor")
    public String becomeSponsor(Model model) {
        model.addAttribute("sponsor", new SponsorShip());
        model.addAttribute("registrations", registrationService.registrationList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "sponsor/becomesponsor";
    }

    @PostMapping("/flushSponsorMoney")
    public String saveSponsorMoney(SponsorShip sponsorShip, Model model) {

        sponsorShipService.save(sponsorShip);

        model.addAttribute("sponsor", sponsorShip);
        model.addAttribute("countDown", countDown.getCountDown());
        return "sponsor/confirmsponsor";
    }
}
