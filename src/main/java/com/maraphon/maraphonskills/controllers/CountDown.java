package com.maraphon.maraphonskills.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class CountDown {

    public String getCountDown() {
        LocalDateTime oldDate = LocalDateTime.now();
        LocalDateTime newDate = LocalDateTime.of(2018, Month.DECEMBER, 27, 9, 0, 0);
        Duration duration = Duration.between(oldDate, newDate);

        int days = (int) (duration.getSeconds() / 86400);
        int hours = (int) ((duration.getSeconds() % 86400) / 3600);
        int minutes = (int) ((duration.getSeconds() % 3600) / 60);

        return days + " дней " + hours + " часов " + minutes + " минут до начала марафона!";
    }
}
