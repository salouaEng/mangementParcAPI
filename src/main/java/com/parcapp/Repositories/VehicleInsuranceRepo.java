package com.parcapp.Repositories;

import com.parcapp.Entities.VehicleInsurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInsuranceRepo extends CrudRepository<VehicleInsurance,Integer> {
}
