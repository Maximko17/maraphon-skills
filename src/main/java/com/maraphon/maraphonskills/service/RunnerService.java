package com.maraphon.maraphonskills.service;

import com.maraphon.maraphonskills.commands.RunnerForm;
import com.maraphon.maraphonskills.domain.Runner;
import com.maraphon.maraphonskills.domain.User;

import java.util.List;

public interface RunnerService {

    List<Runner> getAllRunners();

    Runner findRunnerByEmail(User email);

    RunnerForm findCommandByEmail(User email);

    RunnerForm findCommandById(Long id);

    Runner saveOrUpdate(RunnerForm runnerForm);

    Runner findById(Long id);
}
