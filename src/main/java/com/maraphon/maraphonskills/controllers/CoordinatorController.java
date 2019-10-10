package com.maraphon.maraphonskills.controllers;

import com.maraphon.maraphonskills.commands.RunnerForm;
import com.maraphon.maraphonskills.domain.*;
import com.maraphon.maraphonskills.repository.CharityRepository;
import com.maraphon.maraphonskills.repository.RegistrationRepository;
import com.maraphon.maraphonskills.service.*;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class CoordinatorController {

    private CountDown countDown = new CountDown();
    private RunnerService runnerService;
    private RegistrationService registrationService;
    private CharityService charityService;
    private CountryService countryService;
    private RegistrationEventService registrationEventService;
    private RegistrationRepository registrationRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public CoordinatorController(RunnerService runnerService, RegistrationService registrationService, CharityService charityService,
                                 CountryService countryService, RegistrationEventService registrationEventService,
                                 RegistrationRepository registrationRepository) {
        this.runnerService = runnerService;
        this.registrationService = registrationService;
        this.charityService = charityService;
        this.countryService = countryService;
        this.registrationEventService = registrationEventService;
        this.registrationRepository = registrationRepository;
    }

    @GetMapping("/coordinator/main")
    public String getMainPge(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/main";
    }

    @GetMapping("/coordinator/runners")
    public String getRunners(Model model) {
        model.addAttribute("regRunners", registrationEventService.findAllByMarathonYear((short) 2018));

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/runners";
    }

    @GetMapping("/coordinator/sponsors")
    public String getSponsors(Model model) {
        model.addAttribute("charities", charityService.findAllByOrderBySponsorSumDesc());
        model.addAttribute("totalSum", charityService.totalCharitySum());

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/sponsors";
    }

    @GetMapping("/coordinator/editRunner/{id}")
    public String editRunner(Model model, @PathVariable Long id) {
        Registration registration = registrationService.findByRunner(runnerService.findById(id));

        model.addAttribute("regEvents", registrationEventService.findAllByRegistrationAndYear(registration, (short) 2018));
        model.addAttribute("registration", registration);

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/editrunner";
    }

    @GetMapping("/coordinator/editRunnerProfile/{id}")
    public String editRunnerProfile(Model model, @PathVariable Long id) {
        model.addAttribute("runnerForm", runnerService.findCommandById(id));
        model.addAttribute("countries", countryService.countryList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/editrunnerprofile";
    }

    @PostMapping("/coordinator/saveRunner")
    public String saveOrUpdateRunner(RunnerForm runnerForm, @RequestParam MultipartFile file) throws IOException {
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

        return "redirect:/coordinator/main";
    }

    @GetMapping("/coordinator/showCertificat/{id}")
    public String showCertificat(Model model, @PathVariable Long id) {
        Registration registration = registrationService.findByRunner(runnerService.findById(id));

        model.addAttribute("regEvents", registrationEventService.findAllByRegistrationAndYearNot(registration, (short) 2018));
        model.addAttribute("registration", registration);
        model.addAttribute("totalSum", registrationRepository.totalSponsorSum(registration));

        log.error("'+totalSum+'", registrationRepository.totalSponsorSum(registration));

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/certificat";
    }

    @PostMapping("/sortSponsors")
    public String sortSponsors(Model model, @RequestParam String sort) {

        if (sort.equals("Итоговой сумма")) {
            model.addAttribute("charities", charityService.findAllByOrderBySponsorSumDesc());
        } else {
            model.addAttribute("charities", charityService.findAllByOrderByName());
        }
        model.addAttribute("totalSum", charityService.totalCharitySum());

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/sponsors";
    }

    @PostMapping("/coordinator/sortRunners")
    public String sortRunners(Model model, @RequestParam String paymentStatus, @RequestParam String marathonType, @RequestParam String Attr) {

        if (Attr.equals("Имя")) {
            model.addAttribute("regRunners", registrationEventService.findAllByPaymentStatusAndEventTypeAndYearOrderByFirstName(paymentStatus, marathonType, (short) 2018));
        } else if (Attr.equals("Фамилия")) {
            model.addAttribute("regRunners", registrationEventService.findAllByPaymentStatusAndEventTypeAndYearOrderByLastName(paymentStatus, marathonType, (short) 2018));
        } else if (Attr.equals("Email")) {
            model.addAttribute("regRunners", registrationEventService.findAllByPaymentStatusAndEventTypeAndYearOrderByUsername(paymentStatus, marathonType, (short) 2018));
        }

        model.addAttribute("countDown", countDown.getCountDown());
        return "coordinator/runners";
    }

    @PostMapping("coordinator/uploadCSV")
    public String uploadCSVRunners() {
        List<Registration> registrationList = registrationService.registrationList();
        List<String[]> runnersInfo = new ArrayList<>();

        for (Registration registration : registrationList) {
            String[] items = {String.valueOf(registration.getId()), registration.runner.getEmail().getUsername(), registration.runner.getEmail().getFirstName(),
                    registration.runner.getEmail().getLastName(), registration.runner.getGender(), registration.runner.getCountryCode(), registration.raceKitOption.getType(),
                    registration.runner.getPaymentStatus()};
            runnersInfo.add(items);
        }

        try (FileOutputStream fos = new FileOutputStream(new File(uploadPath + "/csv/newCsvUsers.csv"));
             OutputStreamWriter osw = new OutputStreamWriter(fos,
                     StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(osw)) {

            writer.writeAll(runnersInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/coordinator/runners";
    }

    @PostMapping("coordinator/uploadEmail")
    public String uploadCSVRunnersEmail() {
        List<Runner> runnerList = runnerService.getAllRunners();
        List<String[]> runnersInfo = new ArrayList<>();

        for (Runner runner : runnerList) {
            String[] items = {runner.getEmail().getLastName(), runner.getEmail().getFirstName(), String.valueOf(runner.getId()), runner.getEmail().getUsername()};
            runnersInfo.add(items);
        }

        try (FileOutputStream fos = new FileOutputStream(new File(uploadPath + "/csv/newCsvUsersEmails.csv"));
             OutputStreamWriter osw = new OutputStreamWriter(fos,
                     StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(osw)) {

            writer.writeAll(runnersInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/coordinator/runners";
    }
}
