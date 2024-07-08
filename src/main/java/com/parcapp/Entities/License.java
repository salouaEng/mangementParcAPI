package com.parcapp.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Data
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int licenseId;
    private LocalDate expirationDate;
    private LocalDate issuanceDate;
    private String licenseType;
}

