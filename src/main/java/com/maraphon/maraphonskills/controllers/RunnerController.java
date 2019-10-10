package com.maraphon.maraphonskills.controllers;

import com.maraphon.maraphonskills.commands.RunnerForm;
import com.maraphon.maraphonskills.domain.*;
import com.maraphon.maraphonskills.repository.RegistrationRepository;
import com.maraphon.maraphonskills.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class RunnerController {

    private CountDown countDown = new CountDown();
    private RunnerService runnerService;
    private CharityService charityService;
    private RaceKitOptionService raceKitOptionService;
    private RegistrationRepository registrationRepository;
    private RegistrationService registrationService;
    private CountryService countryService;
    private EventService eventService;
    private RegistrationEventService registrationEventService;

    @Value("${upload.path}")
    private String uploadPath;

    public RunnerController(RunnerService runnerService, CharityService charityService, RaceKitOptionService raceKitOptionService,
                            RegistrationRepository registrationRepository, RegistrationService registrationService, CountryService countryService,
                            EventService eventService, RegistrationEventService registrationEventService) {
        this.runnerService = runnerService;
        this.charityService = charityService;
        this.raceKitOptionService = raceKitOptionService;
        this.registrationRepository = registrationRepository;
        this.registrationService = registrationService;
        this.countryService = countryService;
        this.eventService = eventService;
        this.registrationEventService = registrationEventService;
    }

    @GetMapping("/runner/main")
    public String mainPage(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/main";
    }

    @GetMapping("/runner/registration")
    public String registration(Model model) {
        model.addAttribute("registration", new Registration());
        model.addAttribute("charities", charityService.charityList());
        model.addAttribute("raceKits", raceKitOptionService.raceKitList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/registration";
    }

    @PostMapping("/runner/confirmRegistration")
    public String submitRegistrtion(Registration registration, @AuthenticationPrincipal User user,
                                    @RequestParam String complect, Model model,
                                    @RequestParam List<String> distance) {
        Registration foundRegistration = registrationService.findByRunner(runnerService.findRunnerByEmail(user));

        if (foundRegistration == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            RegistrationStatus registrationStatus = new RegistrationStatus();
            registrationStatus.setId((long) 3);

            Charity charity = registration.charity;
            charity.setSponsorSum((short) (charity.getSponsorSum() + registration.getSponsorTarget()));
            registration.setRunner(runnerService.findRunnerByEmail(user));
            registration.setRaceKitOption(raceKitOptionService.findByType(complect));
            registration.setRegistrationDateTime(dateFormat.format(date));
            registration.setRegistrationStatus(registrationStatus);

            registrationService.save(registration);
        }
        for (String e : distance) {
            Event event = eventService.findById(e);
            RegistrationEvent registrationEvent = new RegistrationEvent();
            registrationEvent.setEvent(event);
            if (foundRegistration == null) {
                registrationEvent.setRegistration(registration);
            } else {
                registrationEvent.setRegistration(foundRegistration);
            }
            registrationEvent.setRaceTime(null);

            registrationEventService.save(registrationEvent);
        }

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/confirmRegistr";
    }

    @GetMapping("/runner/editProfile")
    public String editRunner(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("runnerForm", runnerService.findCommandByEmail(user));
        model.addAttribute("countries", countryService.countryList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/edit";
    }

    @GetMapping("/runner/myResults")
    public String getMyresults(Model model, @AuthenticationPrincipal User user) {
        Registration registration = registrationService.findByRunner(runnerService.findRunnerByEmail(user));
        List<RegistrationEvent> registrationEvent = registrationEventService.findAllByRegistrationAndYearNot(registration, (short) 2018);
        model.addAttribute("myResults", registrationEvent);
        model.addAttribute("registration", registration);

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/myresults";
    }

    @GetMapping("/allResults")
    public String getAllRedults(Model model) {
        List<RegistrationEvent> registrationEvents = registrationEventService.getAllRunnersAndRaceTimeIsNotNull("18", "15");

        model.addAttribute("totalTime", new SimpleDateFormat("HH:mm:ss").format(new Date(TimeUnit.SECONDS.toMillis(registrationEventService.totalRaceTime("FM", (short) 2015, "Мужчина")))));
        model.addAttribute("totalFinishers", registrationEventService.totalFinishers("", (short) 2015, "Мужчина"));
        model.addAttribute("events", registrationEvents);

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/allresults";
    }

    @GetMapping("/runner/mySponsors")
    public String getSponsors(Model model, @AuthenticationPrincipal User user) {
        Registration registration = registrationService.findByRunner(runnerService.findRunnerByEmail(user));
        model.addAttribute("registration", registration);
        model.addAttribute("totalSum", registrationRepository.totalSponsorSum(registration));

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/mysponsor";
    }

    @GetMapping("/becomeRunner")
    public String becomeRunner(Model model) {
        model.addAttribute("runnerForm", new RunnerForm());
        model.addAttribute("countries", countryService.countryList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/becomeRunner";
    }


    @PostMapping("/saveRunner")
    public String saveOrUpdateRunner(RunnerForm runnerForm, HttpServletRequest request, @RequestParam MultipartFile file) throws IOException {
        if (runnerForm.getId() != null) {
            Runner runner = runnerService.findById(runnerForm.getId());
            File prevFile = new File(uploadPath + "/runner/" + runner.getFilename());
            if (prevFile.delete())
                System.out.println(runner.getFilename() + "файл удален");
            else
                System.out.println("Файла " + runner.getFilename() + " не обнаружено");
        }

        if (file != null) {
            File uploadDir = new File(uploadPath + "/runner/");

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String UUIDFile = java.util.UUID.randomUUID().toString();
            String resultFilename = UUIDFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/runner/" + resultFilename));

            runnerForm.setFilename(resultFilename);
        }
        runnerService.saveOrUpdate(runnerForm);

        if (runnerForm.getId() == null) {
            try {
                request.login(runnerForm.getEmail().getUsername(), runnerForm.getEmail().getPassword());
            } catch (ServletException e) {
                log.error("Error while login ", e);
            }
        }
        return "redirect:/runner/main";
    }

    @PostMapping("sortAllRunners")
    public String sortRunners(Model model, @RequestParam Short year, @RequestParam String distance, @RequestParam String gender) {
        List<RegistrationEvent> registrationEvents = registrationEventService.sortRunners(year, distance, gender);
        model.addAttribute("events", registrationEvents);

        model.addAttribute("totalTime", new SimpleDateFormat("HH:mm:ss").format(new Date(TimeUnit.SECONDS.toHours(registrationEventService.totalRaceTime(distance, year, gender)))));
        model.addAttribute("totalFinishers", registrationEventService.totalFinishers(distance, year, gender));

        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/allresults";
    }
}
