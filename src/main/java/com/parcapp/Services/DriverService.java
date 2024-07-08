
package com.parcapp.Services;
import com.parcapp.Entities.*;
import com.parcapp.Repositories.DriverRepo;
import com.parcapp.Repositories.TripRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DriverService implements DriverServiceinteface {
@Autowired
    private DriverRepo driverRepo;
@Autowired
private LicenseService licenseService;
@Autowired
private TripRepo tripRepo;
    @Override
    public List<Driver> getAllDrivers() {

        return (List<Driver>) driverRepo.findAll();}
    @Override
    public Driver getDriverByMatricule(Double matricule) {
        try {
            Optional<Driver> driverOptional = driverRepo.findById(matricule);
            return driverOptional.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    @Override
    public boolean deleteDriver(Double driverId) {
        Optional<Driver> driverOptional = driverRepo.findById(driverId);
        if (driverOptional.isPresent()) {
            driverRepo.deleteById(driverId);
            return true;}
        return false;
    }
    @Transactional
    public Driver saveDriver(Driver driver) {
        try {
            License license=driver.getLicense();
            if (license != null) {
                licenseService.save(license);
                driver.setLicense(license);
            }
            return driverRepo.save(driver);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Driver> getDriversWithValidLicense(List<Driver> drivers, Date dateTrip) {
        List<Driver> validDrivers = new ArrayList<>();
        try {
            for (Driver driver : drivers) {
                if (driver.getLicense() != null && driver.getLicense().getExpirationDate() != null) {
                    if (driver.getLicense().getExpirationDate().isAfter(dateTrip.toLocalDate())) {
                        validDrivers.add(driver);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validDrivers;
    }
    public boolean isDriverAvailable(Driver driver ,Date startDate,Date endDate){
        List<Trip> trips = tripRepo.findByDriver(driver);
        for (Trip trip : trips) {
            if (isOverlapping(trip.getDepartureDate(), trip.getArrivalDate(), startDate, endDate)) {
                return false;
            }}
        return true;
    }
    private boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);}
    @Override
    public List<Driver> availableDrivers(List<Driver> drivers, Date depart, Date arrive) {
        drivers.removeIf(driver -> !isDriverAvailable(driver, depart, arrive));
        return drivers;}
    @Override
    public List<Driver> FinalListDriver(Date startDate, Date endDate){
        List<Driver> drivers=getAllDrivers();
        List<Driver> drivers2=availableDrivers(drivers,startDate,endDate);
        return getDriversWithValidLicense(drivers2,endDate);


    }

}

