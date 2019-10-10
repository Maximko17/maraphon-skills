package com.maraphon.maraphonskills.controllers;

import com.maraphon.maraphonskills.commands.CharityForm;
import com.maraphon.maraphonskills.commands.UserForm;
import com.maraphon.maraphonskills.domain.Charity;
import com.maraphon.maraphonskills.domain.Inventory;
import com.maraphon.maraphonskills.domain.RegistrationEvent;
import com.maraphon.maraphonskills.domain.Volunteer;
import com.maraphon.maraphonskills.repository.InventoryRepository;
import com.maraphon.maraphonskills.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Controller
public class AdminController {

    private CountDown countDown = new CountDown();
    private CharityService charityService;
    private UserService userService;
    private VolunteerService volunteerService;
    private AuthorityService authorityService;
    private RegistrationService registrationService;
    private InventoryService inventoryService;

    @Value("${upload.path}")
    private String uploadPath;

    public AdminController(CharityService charityService, UserService userService, VolunteerService volunteerService,
                           AuthorityService authorityService, RegistrationService registrationService,
                           InventoryService inventoryService) {
        this.charityService = charityService;
        this.userService = userService;
        this.volunteerService = volunteerService;
        this.authorityService = authorityService;
        this.registrationService = registrationService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/admin/main")
    public String getMainPage(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/main";
    }

    @GetMapping("/admin/users")
    public String getUsersPage(Model model) {
        model.addAttribute("authorities", authorityService.getAuthorityList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/users";
    }

    @GetMapping("/admin/volunteers")
    public String getVolunteerPage(Model model) {
        model.addAttribute("volunteers", volunteerService.getAllVolunteers());

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/volunteers";
    }

    @PostMapping("/downloadVolunteerFromCSV")
    public String uploadCSVVolunteers(@RequestParam MultipartFile file) throws IOException {

        File uploadDir = new File(uploadPath + "/csv/");

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        file.transferTo(new File(uploadPath + "/csv/" + file.getOriginalFilename()));

        // открываем файл
        BufferedReader reader = new BufferedReader(new FileReader(uploadPath + "/csv/" + file.getOriginalFilename()));

        // считываем построчно
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<Volunteer> allVolunteers = volunteerService.getAllVolunteers();
        List<Volunteer> volList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            Volunteer vol = new Volunteer();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    for (Volunteer volunteer : allVolunteers) {
                        if (volunteer.getId().equals(Long.valueOf(data))) {
                            vol.setId(volunteer.getId());
                        } else {
                            vol.setId(Long.valueOf(data));
                        }
                    }
                else if (index == 1)
                    vol.setFirstName(data);
                else if (index == 2)
                    vol.setLastName(data);
                else if (index == 3)
                    vol.setCountryCode(data);
                else if (index == 4)
                    vol.setGender(data);
                else
                    System.out.println("Некорректные данные::" + data);
                index++;
            }
            index = 0;
            volList.add(vol);
        }

        volunteerService.saveAll(volList);
        //закрываем наш ридер
        reader.close();

        System.out.println(volList);

        return "redirect:/admin/volunteers";
    }

    @GetMapping("/admin/chrityOrgs")
    public String getCharityPage(Model model) {
        model.addAttribute("charities", charityService.charityList());

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/chrityorgs";
    }

    @GetMapping("/admin/newCharity")
    public String newCharity(Model model) {
        model.addAttribute("charityForm", new CharityForm());

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/createorupdatecharity";
    }

    @GetMapping("/admin/inventory")
    public String getInventoryPage(Model model) {
        model.addAttribute("totalRegRunners", registrationService.registrationList());
        model.addAttribute("kitA", registrationService.findAllByKitype("A"));
        model.addAttribute("kitB", registrationService.findAllByKitype("B"));
        model.addAttribute("kitC", registrationService.findAllByKitype("C"));
        model.addAttribute("inventory", inventoryService.findById((short) 1));

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/inventory";
    }

    @GetMapping("/admin/newInventory")
    public String newInventory(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        model.addAttribute("inventory", new Inventory());

        return "admin/newinventory";
    }

    @PostMapping("/addNewInvItem")
    public String saveInventory(Inventory inventory) {
        inventoryService.save(inventory);

        return "redirect:/admin/inventory";
    }

    @GetMapping("/admin/editUser/{username}")
    public String editUser(Model model, @PathVariable String username) {
        model.addAttribute("userForm", userService.getUserCommandByEmail(username));

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/createorupdate";
    }

    @GetMapping("/admin/newUser")
    public String newUser(Model model) {
        model.addAttribute("userForm", new UserForm());

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/createorupdate";
    }

    @GetMapping("/admin/uploadVolunteers")
    public String uploadVolunteers(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/uploadvolunteers";
    }

    @GetMapping("/admin/createOrUpdateCharity/{id}")
    public String createOrUpdateCharity(Model model, @PathVariable Long id) {
        model.addAttribute("path", uploadPath);
        model.addAttribute("charityForm", charityService.getCharityCommandById(id));

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/createorupdatecharity";
    }

    @PostMapping("/admin/saveCharityOrg")
    public String saveCharityOrg(@RequestParam MultipartFile file, CharityForm charityForm) throws IOException {

        if (charityForm.getId() != null) {
            Charity charity = charityService.getCharityById(charityForm.getId());
            File prevFile = new File(uploadPath + "/charity/" + charity.getFileName());
            if (prevFile.delete())
                System.out.println(charity.getFileName() + "файл удален");
            else
                System.out.println("Файла " + charity.getFileName() + " не обнаружено");
        }
        if (file != null) {
            File uploadDir = new File(uploadPath + "/charity/");

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String UUIDFile = java.util.UUID.randomUUID().toString();
            String resultFilename = UUIDFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/charity/" + resultFilename));

            charityForm.setFileName(resultFilename);
        }

        charityService.save(charityForm);

        return "redirect:/admin/chrityOrgs";
    }

    @PostMapping("/admin/sortVolunteers")
    public String sortByName(Model model, @RequestParam String selectSort) {
        switch (selectSort) {
            case "Имя":
                model.addAttribute("volunteers", volunteerService.getAllVolunteersOrderByFirstName());
                break;
            case "Фамилия":
                model.addAttribute("volunteers", volunteerService.getAllVolunteersOrderByLastName());
                break;
            case "Страна":
                model.addAttribute("volunteers", volunteerService.getAllVolunteersOrderByCountry());
                break;
        }

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/volunteers";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(UserForm userForm, @RequestParam String authority) {

        userService.saveOrUpdate(userForm, authority);

        return "redirect:/admin/users";
    }

    @PostMapping("/admin/sortUser")
    public String sortUser(Model model, @RequestParam String sortByRole, @RequestParam String sortByAttr, @RequestParam String sortBySearch) {

        if (sortBySearch.equals("")) {
            if (sortByRole.equals("All ROLES") && sortByAttr.equals("Имя")) {
                model.addAttribute("authorities", authorityService.findAllByOrderByUser_FirstName());
            } else if (sortByRole.equals("All ROLES") && sortByAttr.equals("Фамилия")) {
                model.addAttribute("authorities", authorityService.findAllByOrderByUser_LastName());
            } else if (sortByRole.equals("All ROLES") && sortByAttr.equals("Email")) {
                model.addAttribute("authorities", authorityService.findAllByOrderByUser_Username());
            } else if (sortByAttr.equals("Имя")) {
                model.addAttribute("authorities", authorityService.findByAuthorityOrderByUser_FirstName(sortByRole));
            } else if (sortByAttr.equals("Фамилия")) {
                model.addAttribute("authorities", authorityService.findByAuthorityOrderByUser_LastName(sortByRole));
            } else if (sortByAttr.equals("Email")) {
                model.addAttribute("authorities", authorityService.findByAuthorityOrderByUser_Username(sortByRole));
            }
        } else {
            model.addAttribute("authorities", authorityService.findBySearch(sortBySearch, sortBySearch, sortBySearch));
        }

        model.addAttribute("countDown", countDown.getCountDown());
        return "admin/users";
    }
}
