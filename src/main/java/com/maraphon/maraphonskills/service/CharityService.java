package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.CharityForm;
import com.maraphon.maraphonskills.domain.Charity;

import java.util.List;

public interface CharityService {

    List<Charity> charityList();

    Charity getCharityById(Long id);

    CharityForm getCharityCommandById(Long id);

    Charity save(CharityForm charityForm);

    Integer totalCharitySum();

    List<Charity> findAllByOrderByName();

    List<Charity> findAllByOrderBySponsorSumDesc();

}
