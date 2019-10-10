package com.maraphon.maraphonskills.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Inventory {

    @Id
    private Short id;

    private Short number;
    private Short rfid;
    private Short baseball;
    private Short bottleWater;
    private Short tShirt;
    private Short suvBooklet;

}
