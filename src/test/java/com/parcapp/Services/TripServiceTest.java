package com.parcapp.Services;

import com.parcapp.Entities.Driver;
import com.parcapp.Entities.Trip;
import com.parcapp.Entities.Vehicle;
import com.parcapp.Repositories.TripRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TripServiceTest {

    @Autowired
    private TripService tripService;
    @Test
    void testCreateTripWithAssignedDriverAndVehicle() throws Exception {
        Date departureDate = Date.valueOf("2024-05-30");
        Date arrivalDate = Date.valueOf("2024-06-05");
        String departure = "Departure";
        String destination = "Destination";
        int nbrPassagers = 5;
        String type = "CAR";

        Trip newTrip = tripService.createTripWithAssignedDriverAndVehicle(departureDate, arrivalDate, departure, destination, nbrPassagers, type);
        assertNotNull(newTrip);
    }

    @Test
    void testGetAllTrips() {
        List<Trip> actualTrips = tripService.getAllTrips();
        assertEquals(1, actualTrips.size());
    }

    @Test
    void testGetTripById() throws Exception {
        // Your test logic here
    }

    // Add other test methods for TripService as needed
}
