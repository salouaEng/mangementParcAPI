package com.parcapp.Entities;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
    private int vehicleCapacity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="registration_certificate_id")
    private RegistrationCertificate registrationCertificate;

    private String vehicleType;
    private boolean controlleVisit;
    @OneToOne
    @JoinColumn(name="vehicle_insurance_id")
    private VehicleInsurance vehicleInsurance;
    private boolean vignette;
    private boolean isAvailable;
}

