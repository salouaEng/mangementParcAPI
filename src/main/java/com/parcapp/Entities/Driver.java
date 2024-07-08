package com.parcapp.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Double matricule;
    private int driverIdCard;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_id")
    private License license;
    private boolean isAvailable;


}
