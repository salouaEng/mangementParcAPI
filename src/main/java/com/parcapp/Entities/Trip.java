package com.parcapp.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    private Date departureDate;
    private Date arrivalDate;
    private int departureHour;
    private int arrivalHour;
    private int numberOfPassager;
    private String deparature;
    private String destination;
    @ManyToOne
    @JoinColumn(name = "matricule")
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}


