package com.parcapp.Services;
import com.parcapp.Entities.Driver;
import com.parcapp.Entities.Vehicle;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DriverServiceinteface {
     List<Driver> getAllDrivers();
     Driver getDriverByMatricule(Double matricule);
     boolean deleteDriver(Double driverId);
     Driver saveDriver(Driver driver);
     List<Driver> getDriversWithValidLicense(List<Driver> drivers, Date dateTrip);
     List<Driver> availableDrivers(List<Driver> drivers, Date depart, Date arrive);
     List<Driver> FinalListDriver(Date startDate, Date endDate);
     /*
     public List<Driver> getDriverWithValidateLicense();
     public List<Driver> getAvailableDriver(LocalDate tripStart, LocalDate tripEnd);
     public boolean isDriverAvailable(Long driverMatricule, LocalDate tripStart, LocalDate tripEnd);*/
}
