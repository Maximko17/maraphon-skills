package com.maraphon.maraphonskills.commands;

import com.maraphon.maraphonskills.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RunnerForm {

    private Long id;
    private String gender;
    private String dateOfBirth;

    private User email;

    private String countryCode;
    private String paymentStatus;
    private String filename;
}
