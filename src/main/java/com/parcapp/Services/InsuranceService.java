package com.parcapp.Services;

import com.parcapp.Entities.VehicleInsurance;
import com.parcapp.Repositories.VehicleInsuranceRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class InsuranceService implements  InsuranceInterface{
@Autowired
private VehicleInsuranceRepo insurance;
    @Override
    public VehicleInsurance save(VehicleInsurance ins) {
        return insurance.save(ins);
    }
    @Override
    public List<VehicleInsurance> getAll() {
        return (List<VehicleInsurance>) insurance.findAll();
    }
    @Override
    public Optional<VehicleInsurance> getById(int id) {
        return insurance.findById(id);
    }
}
