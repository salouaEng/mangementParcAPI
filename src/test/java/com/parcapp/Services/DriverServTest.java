package com.parcapp.Services;
import com.parcapp.Entities.Driver;
import com.parcapp.Entities.License;
import com.parcapp.Entities.Trip;

import com.parcapp.Entities.LicenseType;
import com.parcapp.Entities.LicenseTypeEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

import java.sql.Date;
import java.util.ArrayList;
@SpringBootTest
class DriverServTest {
    @Autowired
    LicenseTypeService licensetTypeService;
    @Autowired
    LicenseService licenseService;
    @Autowired
    DriverService driverService;
    @Autowired
    TripService tripService;

    @Test
    public void testGetAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        assertNotNull(drivers, "La liste des conducteurs ne doit pas être nulle");
        drivers.forEach(System.out::println);
    }
    @Test
    public void getDriverByMatricule() {
        Double matricule = 7.0;
        Driver driver = driverService.getDriverByMatricule(matricule);
        assertNotNull(driver, "Le conducteur ne doit pas être nul");
        System.out.print(driver);
    }
    @Test
    void testDeleteDriver(){
        Double driverId = 123.0;
        boolean result = driverService.deleteDriver(driverId);
        assertFalse(result); // Assuming there's no driver with this ID initially
    }

    @Test
    void testSaveDriver() {
        Driver driver = new Driver();
        driver.setDateOfBirth(LocalDate.parse("1999-02-02"));
        driver.setFirstName("MUSTAPHA");
        driver.setLastName("ouissa");
        License license = new License();
        license.setLicenseType("TYPE_B");
        license.setIssuanceDate(LocalDate.parse("2002-03-12"));
        license.setExpirationDate(LocalDate.parse("2025-01-01")); // Correction du format de la date d'expiration
        driver.setLicense(license);
        Driver savedDriver = driverService.saveDriver(driver);
        assertNotNull(savedDriver);
        assertNotNull(savedDriver.getLicense());
    }
    @Test
    void testGetDriversWithValidLicense() {
        List<Driver> drivers = new ArrayList<>();
        Driver validDriver = new Driver();
        License validLicense = new License();
        validLicense.setExpirationDate(Date.valueOf(LocalDate.now().plusDays(1)).toLocalDate()); // Licence valide
        validDriver.setLicense(validLicense);
        drivers.add(validDriver);
        Driver expiredDriver = new Driver();
        License expiredLicense = new License();
        expiredLicense.setExpirationDate(Date.valueOf(LocalDate.now().minusDays(1)).toLocalDate()); // Licence expirée
        expiredDriver.setLicense(expiredLicense);
        drivers.add(expiredDriver);
        Date dateTrip = Date.valueOf(LocalDate.now());
        List<Driver> validDrivers = driverService.getDriversWithValidLicense(drivers, dateTrip);
        assertEquals(1, validDrivers.size());
        assertEquals(validDriver, validDrivers.get(0));
    }
    @Test
    void testAvailableDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers=driverService.getAllDrivers();
        Date depart = Date.valueOf("2024-05-30");
        Date arrive = Date.valueOf("2024-06-05");
        List<Driver> availableDrivers = driverService.availableDrivers(drivers, depart, arrive);
        assertNotNull(availableDrivers);
        for (Driver driver : availableDrivers) {
            assertTrue(driverService.isDriverAvailable(driver, depart, arrive));
        }

    }
    @Test
    void testFinalListDriver() {
        Date startDate = Date.valueOf("2024-05-30");
        Date endDate = Date.valueOf("2024-06-05");
        List<Driver> finalDriverList = driverService.FinalListDriver(startDate, endDate);
        assertNotNull(finalDriverList);
        for (Driver driver : finalDriverList) {
            assertTrue(driverService.isDriverAvailable(driver, startDate, endDate));
        }}

}

