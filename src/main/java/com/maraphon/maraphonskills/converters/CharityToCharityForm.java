package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.CharityForm;
import com.maraphon.maraphonskills.domain.Charity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CharityToCharityForm implements Converter<Charity, CharityForm> {

    @Override
    public CharityForm convert(Charity charity) {
        CharityForm charityForm = new CharityForm();

        charityForm.setId(charity.getId());
        charityForm.setName(charity.getName());
        charityForm.setDescription(charity.getDescription());
        charityForm.setFileName(charity.getFileName());
        charityForm.setSponsorSum(charity.getSponsorSum());

        return charityForm;
    }
}
