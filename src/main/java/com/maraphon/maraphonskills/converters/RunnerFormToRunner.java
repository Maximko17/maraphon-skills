package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.RunnerForm;
import com.maraphon.maraphonskills.domain.Authorities;
import com.maraphon.maraphonskills.domain.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class RunnerFormToRunner implements Converter<RunnerForm, Runner> {

    @Override
    public Runner convert(RunnerForm runnerForm) {
        Runner runner = new Runner();

        runner.setId(runnerForm.getId());
        runner.setEmail(runnerForm.getEmail());
        runner.setCountryCode(runnerForm.getCountryCode());
        runner.setDateOfBirth(runnerForm.getDateOfBirth());
        runner.setGender(runnerForm.getGender());
        if (runnerForm.getId() == null) {
            runner.setPaymentStatus("Оплата не подтверждена");

            Authorities authorities = new Authorities();
            authorities.setAuthority("ROLE_RUNNER");
            authorities.setUser(runnerForm.getEmail());
            Set<Authorities> authoritiesList = new HashSet<>();
            authoritiesList.add(authorities);

            runner.getEmail().setAuthorities(authoritiesList);
        } else {
            runner.setPaymentStatus(runnerForm.getPaymentStatus());
        }
        runner.setFilename(runnerForm.getFilename());

        return runner;
    }
}
