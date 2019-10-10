package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.CharityForm;
import com.maraphon.maraphonskills.converters.CharityFormToCharity;
import com.maraphon.maraphonskills.converters.CharityToCharityForm;
import com.maraphon.maraphonskills.domain.Charity;
import com.maraphon.maraphonskills.repository.CharityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CharityServiceImpl implements CharityService {

    private CharityRepository charityRepository;
    private CharityToCharityForm charityToCharityForm;
    private CharityFormToCharity charityFormToCharity;

    public CharityServiceImpl(CharityRepository charityRepository, CharityToCharityForm charityToCharityForm, CharityFormToCharity charityFormToCharity) {
        this.charityRepository = charityRepository;
        this.charityToCharityForm = charityToCharityForm;
        this.charityFormToCharity = charityFormToCharity;
    }

    @Override
    public List<Charity> charityList() {
        List<Charity> charityList = new ArrayList<>();
        charityRepository.findAll().forEach(charityList::add);

        return charityList;
    }

    @Override
    public Charity getCharityById(Long id) {
        return charityRepository.findById(id).orElse(null);
    }

    @Override
    public CharityForm getCharityCommandById(Long id) {
        return charityToCharityForm.convert(getCharityById(id));
    }

    @Override
    public Charity save(CharityForm charityForm) {
        Charity charity = charityFormToCharity.convert(charityForm);

        charityRepository.save(charity);

        return charity;
    }

    @Override
    public Integer totalCharitySum() {
        return charityRepository.totalCharitySum();
    }

    @Override
    public List<Charity> findAllByOrderByName() {
        return charityRepository.findAllByOrderByName();
    }

    @Override
    public List<Charity> findAllByOrderBySponsorSumDesc() {
        return charityRepository.findAllByOrderBySponsorSumDesc();
    }

}
