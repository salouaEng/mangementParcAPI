package com.parcapp.Services;

import com.parcapp.Entities.VehicleInsurance;

import java.util.List;
import java.util.Optional;

public interface InsuranceInterface {
    public VehicleInsurance save(VehicleInsurance ins);
    public List<VehicleInsurance> getAll();
    public Optional<VehicleInsurance> getById(int id);
}
