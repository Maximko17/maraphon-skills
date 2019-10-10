package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.RaceKitOption;
import com.maraphon.maraphonskills.repository.RaceKitOptionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceKitOptionServiceImpl implements RaceKitOptionService {

    private RaceKitOptionRepository raceKitOptionRepository;

    public RaceKitOptionServiceImpl(RaceKitOptionRepository raceKitOptionRepository) {
        this.raceKitOptionRepository = raceKitOptionRepository;
    }

    @Override
    public List<RaceKitOption> raceKitList() {
        List<RaceKitOption> raceKitOptionList = new ArrayList<>();
        raceKitOptionRepository.findAll().forEach(raceKitOptionList::add);

        return raceKitOptionList;
    }

    @Override
    public RaceKitOption findByType(String type) {
        return raceKitOptionRepository.findByType(type);
    }
}
