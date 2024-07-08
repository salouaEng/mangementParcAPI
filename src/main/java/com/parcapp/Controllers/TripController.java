package com.parcapp.Controllers;

import com.parcapp.Entities.Trip;
import com.parcapp.Services.TripService;
import com.parcapp.Services.TripServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.parcapp.Controllers.CreateTripRequest;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripServiceInterface tripService;

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable int tripId) {
        try {
            Trip trip = tripService.getTripById(tripId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Trip> saveTrip(@RequestBody Trip trip) {
        try {
            Trip savedTrip = tripService.saveTrip(trip);
            return new ResponseEntity<>(savedTrip, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createTripWithAssignedDriverAndVehicle(@RequestBody CreateTripRequest request) {
        try {
            Trip newTrip = tripService.createTripWithAssignedDriverAndVehicle(
                    request.getDepartureDate(), request.getArrivalDate(), request.getDeparture(),
                    request.getDestination(), request.getNbrPassagers(), request.getType());
            return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
            }   catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
