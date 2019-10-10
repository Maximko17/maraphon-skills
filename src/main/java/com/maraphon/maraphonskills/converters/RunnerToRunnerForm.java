package com.maraphon.maraphonskills.converters;

import com.maraphon.maraphonskills.commands.RunnerForm;
import com.maraphon.maraphonskills.domain.Runner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RunnerToRunnerForm implements Converter<Runner, RunnerForm> {

    @Override
    public RunnerForm convert(Runner runner) {
        RunnerForm runnerForm = new RunnerForm();

        runnerForm.setId(runner.getId());
        runnerForm.setEmail(runner.getEmail());
        runnerForm.setCountryCode(runner.getCountryCode());
        runnerForm.setDateOfBirth(runner.getDateOfBirth());
        runnerForm.setGender(runner.getGender());
        runnerForm.setPaymentStatus(runner.getPaymentStatus());
        runnerForm.setFilename(runner.getFilename());

        return runnerForm;
    }
}


