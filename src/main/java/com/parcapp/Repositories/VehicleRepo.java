package com.parcapp.Repositories;

import com.parcapp.Entities.RegistrationCertificate;
import com.parcapp.Entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehicleRepo extends CrudRepository<Vehicle,Integer> {
    List<Vehicle> findByVehicleType(String type);
}
