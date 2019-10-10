package com.maraphon.maraphonskills.controllers;

import com.maraphon.maraphonskills.repository.CharityRepository;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.Math.round;

@Slf4j
@Controller
public class LearnMoreController {

    private CountDown countDown = new CountDown();
    private CharityRepository charityRepository;

    public LearnMoreController(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    @GetMapping("/aboutEvent")
    public String getAboutEventPage(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/aboutevent";
    }

    @GetMapping("/marathonCompare")
    public String getMarathonCompare(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/marathoncompare";
    }

    @GetMapping("/charityOrgList")
    public String getCharityOrgList(Model model) {
        model.addAttribute("charityList", charityRepository.findAll());

        return "learnmore/charityorglist";
    }

    @GetMapping("/calculatorBMI")
    public String getcalculatorBMI(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/calculatorbmi";
    }

    @GetMapping("/calculatorBMR")
    public String getCalculatorBMR(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/calculatorbmr";
    }

    @GetMapping("/interactiveMap")
    public String getInteractiveMap() {
        return "learnmore/interactivemap";
    }


    @PostMapping("/calcBMR")
    public String calcBmr(Model model, @RequestParam("gender") String gender,
                          @RequestParam int height,
                          @RequestParam int weight,
                          @RequestParam int age) {

        if (gender.equals("male")) {
            long bmr = Math.round((10 * weight) + (6.25 * height) - (5 * age) + 5);
            model.addAttribute("bmr", bmr);
        } else {
            long bmr = Math.round((10 * weight) + (6.25 * height) - (5 * age) - 161);
            model.addAttribute("bmr", bmr);
            log.error("bmr  " + bmr);
        }

        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/calculatorbmr";
    }

    @PostMapping("/calcBMI")
    public String calcBmi(Model model, @RequestParam int height, @RequestParam int weight) {

        float heightImMetr = (float) (height / 100.0);
        float bmi = weight / (heightImMetr * heightImMetr);

        if (bmi < 19) {
            model.addAttribute("bmi", bmi);
            model.addAttribute("conclusion", "Недостаточный вес");
            model.addAttribute("img", "bmi-underweight-icon.png");
        } else if (bmi > 19 && bmi <= 25) {
            model.addAttribute("bmi", bmi);
            model.addAttribute("conclusion", "Здоровый");
            model.addAttribute("img", "bmi-healthy-icon.png");
        } else if (bmi > 25 && bmi <= 30) {
            model.addAttribute("bmi", bmi);
            model.addAttribute("conclusion", "Избыточный вес");
            model.addAttribute("img", "bmi-overweight-icon.png");
        } else if (bmi > 30) {
            model.addAttribute("bmi", bmi);
            model.addAttribute("conclusion", "Ожирение");
            model.addAttribute("img", "bmi-obese-icon.png");
        }

        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/calculatorbmi";
    }

}
