package com.parcapp.Repositories;

import com.parcapp.Entities.Driver;
import com.parcapp.Entities.Trip;
import com.parcapp.Entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepo extends CrudRepository<Trip, Integer> {
    List<Trip> findByVehicle(Vehicle vehicle);
    List<Trip> findByDriver(Driver driver);
    Trip save(Trip trip);

        }
