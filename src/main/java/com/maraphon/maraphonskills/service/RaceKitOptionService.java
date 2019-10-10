package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.RaceKitOption;

import java.util.List;

public interface RaceKitOptionService {

    List<RaceKitOption> raceKitList();

    RaceKitOption findByType(String type);

}
