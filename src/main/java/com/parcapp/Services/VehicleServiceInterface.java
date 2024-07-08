package com.parcapp.Services;

import com.parcapp.Entities.Vehicle;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface VehicleServiceInterface {
    public List<String> getAllVehicleType();
    public List<Vehicle> getByVehicleType(String type);
    public boolean documentsStatus(int vehicleId, Date travelDate);
    public List<Vehicle> filterValidDocuments(List<Vehicle> vehicles, Date travelDate);
    public boolean isVehicleAvailable(Vehicle vehicle, Date depart, Date arrive);
public List<Vehicle> availableVehicles(List<Vehicle>vehicles ,Date depart,Date arrive);
public List<Vehicle> listeFinal(Date depart,Date arrive ,String type);
    }
