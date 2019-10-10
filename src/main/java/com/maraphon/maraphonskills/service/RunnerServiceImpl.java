package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.RunnerForm;
import com.maraphon.maraphonskills.converters.RunnerFormToRunner;
import com.maraphon.maraphonskills.converters.RunnerToRunnerForm;
import com.maraphon.maraphonskills.domain.Runner;
import com.maraphon.maraphonskills.domain.User;
import com.maraphon.maraphonskills.repository.RunnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RunnerServiceImpl implements RunnerService {

    private RunnerRepository runnerRepository;
    private RunnerToRunnerForm runnerToRunnerForm;
    private RunnerFormToRunner runnerFormToRunner;

    public RunnerServiceImpl(RunnerRepository runnerRepository, RunnerToRunnerForm runnerToRunnerForm, RunnerFormToRunner runnerFormToRunner) {
        this.runnerRepository = runnerRepository;
        this.runnerToRunnerForm = runnerToRunnerForm;
        this.runnerFormToRunner = runnerFormToRunner;
    }

    @Override
    public List<Runner> getAllRunners() {
        List<Runner> runnerList = new ArrayList<>();
        runnerRepository.findAll().forEach(runnerList::add);

        return runnerList;
    }

    @Override
    public Runner findRunnerByEmail(User email) {
        return runnerRepository.findByEmail(email);
    }

    @Override
    public RunnerForm findCommandByEmail(User email) {
        return runnerToRunnerForm.convert(findRunnerByEmail(email));
    }

    @Override
    public RunnerForm findCommandById(Long id) {
        return runnerToRunnerForm.convert(findById(id));
    }

    @Override
    public Runner saveOrUpdate(RunnerForm runnerForm) {
        Runner runner = runnerFormToRunner.convert(runnerForm);
        runnerRepository.save(runner);

        return runner;
    }

    @Override
    public Runner findById(Long id) {
        return runnerRepository.findById(id).orElse(null);
    }
}
