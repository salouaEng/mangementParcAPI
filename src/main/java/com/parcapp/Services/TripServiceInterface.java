package com.parcapp.Services;
import com.parcapp.Entities.Driver;
import com.parcapp.Entities.Trip;
import com.parcapp.Entities.Vehicle;
import org.springframework.data.crossstore.ChangeSetPersister;


import  java.sql.Date;
import java.util.List;
public interface TripServiceInterface {
    List<Trip> getAllTrips();
    Trip getTripById(int tripId) throws ChangeSetPersister.NotFoundException;
    Trip saveTrip(Trip trip) throws Exception;
    Driver findEligibleDriver(Date departureDate, Date arrivalDate);
    Vehicle findValidVehicle(Date departureDate, Date arrivalDate, String type);
    Trip createTripWithAssignedDriverAndVehicle(Date departureDate, Date arrivalDate, String departure, String destination, int nbrPassagers, String type) throws Exception;

}

