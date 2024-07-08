package com.parcapp.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class RegistrationCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regCertifId;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "expiry_date")
    private Date expiryDate;
    @Column(name = "number_of_seats")// nbr de place
    private int numberOfSeats;
    @Column(name = "fuel_type")
    private String fuelType;
}
