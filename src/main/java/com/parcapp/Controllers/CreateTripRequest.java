package com.parcapp.Controllers;
import java.sql.Date;

public class CreateTripRequest {
    private Date departureDate;
    private Date arrivalDate;
    private String departure;
    private String destination;
    private int nbrPassagers;
    private String type;
    public java.sql.Date getDepartureDate() {
        return  departureDate;
    }

    public java.sql.Date getArrivalDate() {
        return arrivalDate;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getNbrPassagers() {
        return nbrPassagers;
    }

    public String getType() {
        return type;
    }}


