
package com.parcapp.Services;

import com.parcapp.Entities.Driver;
import com.parcapp.Entities.Trip;
import com.parcapp.Entities.Vehicle;
import com.parcapp.Repositories.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
@Service
public class TripService implements TripServiceInterface {
   @Autowired
    private TripRepo tripRepository;
   @Autowired
   private DriverService driverService;
   @Autowired
   private VehicleService vehicleService;
    @Override
    public List<Trip> getAllTrips() {
        return (List<Trip>) tripRepository.findAll();
    }
    @Override
    public Trip getTripById(int tripId) throws ChangeSetPersister.NotFoundException {
          Optional<Trip> tripOptional = tripRepository.findById(tripId);
          if (tripOptional.isPresent()) {
              return tripOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();}
    }
    @Override
    public Trip saveTrip(Trip trip) throws Exception {
        return tripRepository.save(trip);
    }
    @Override
    public Driver findEligibleDriver(Date departureDate, Date arrivalDate) {
        List<Driver> availableDrivers = driverService.FinalListDriver(departureDate,  arrivalDate);
        if (!availableDrivers.isEmpty()) {
            return availableDrivers.get(0);
        } else {
            return null;
        }}
    @Override
    public Vehicle findValidVehicle(Date departureDate,Date arrivalDate,String type) {
        List<Vehicle> availableVehicles = vehicleService.listeFinal(departureDate,arrivalDate,type);
        if (!availableVehicles.isEmpty()) {
            return availableVehicles.get(0);
        } else {
            return null;
        }
    }
    @Override
    public Trip createTripWithAssignedDriverAndVehicle(Date departureDate, Date arrivalDate, String departure, String destination, int nbrPassagers,String type) throws Exception {
        Driver eligibleDriver = findEligibleDriver(departureDate, arrivalDate);
        if (eligibleDriver == null) {
            throw new Exception("Aucun conducteur éligible disponible pour ce voyage.");
        }
        Vehicle validVehicle = findValidVehicle(departureDate, arrivalDate,type);
        if (validVehicle == null) {
            throw new Exception("Aucun véhicule valide disponible pour ce voyage.");
        }
        Trip newTrip = Trip.builder()
                .deparature(departure)
                .destination(destination)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .numberOfPassager(nbrPassagers)
                .driver(eligibleDriver)
                .vehicle(validVehicle)
                .build();
        return tripRepository.save(newTrip);
    }



}
