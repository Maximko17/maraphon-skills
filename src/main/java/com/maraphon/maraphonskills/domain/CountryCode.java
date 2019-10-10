package com.maraphon.maraphonskills.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CountryCode {

    @Id
    private String countryCode;
    private String countryName;
    private String fileName;
}
