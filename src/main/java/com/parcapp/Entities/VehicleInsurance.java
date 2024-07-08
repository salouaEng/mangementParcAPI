package com.parcapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class VehicleInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insuranceId;
    private LocalDate experationtDate;
    private  LocalDate issuanceDate;
}
