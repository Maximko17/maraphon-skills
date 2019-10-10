package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.CharityForm;
import com.maraphon.maraphonskills.domain.Charity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class CharityFormToCharity implements Converter<CharityForm, Charity> {

    @Override
    public Charity convert(CharityForm charityForm) {
        Charity charity = new Charity();

        if (charityForm.getId() != null && !isEmpty(charityForm.getId())) {
            charity.setId(charityForm.getId());
        }
        charity.setName(charityForm.getName());
        charity.setDescription(charityForm.getDescription());
        charity.setFileName(charityForm.getFileName());

        if (charityForm.getSponsorSum() == null) {
            charity.setSponsorSum((short) 0);
        } else {
            charity.setSponsorSum(charityForm.getSponsorSum());
        }

        return charity;
    }
}
