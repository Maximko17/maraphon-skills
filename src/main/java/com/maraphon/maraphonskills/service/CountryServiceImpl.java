package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.domain.CountryCode;
import com.maraphon.maraphonskills.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryCode> countryList() {
        List<CountryCode> countryCodeList = new ArrayList<>();
        countryRepository.findAll().forEach(countryCodeList::add);

        return countryCodeList;
    }
}
