package com.parcapp.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LicenseTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LicenseType licenceType;
}
