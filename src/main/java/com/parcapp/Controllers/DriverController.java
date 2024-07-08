package com.parcapp.Controllers;
import com.parcapp.Entities.Driver;
import com.parcapp.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @GetMapping("/all")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
    @GetMapping("/{matricule}")
    public Driver getDriverByMatricule(@PathVariable("matricule") Double matricule) {
        return driverService.getDriverByMatricule(matricule);
    }
    @DeleteMapping("/{driverId}")
    public boolean deleteDriver(@PathVariable("driverId") Double driverId) {
        return driverService.deleteDriver(driverId);
    }
    @PostMapping("/save")
    public Driver saveDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }
    @GetMapping("/valid-license")
    public List<Driver> getDriversWithValidLicense(@RequestParam Date dateTrip) {
        List<Driver> allDrivers = driverService.getAllDrivers();
        return driverService.getDriversWithValidLicense(allDrivers, dateTrip);
    }
    @GetMapping("/available")
    public List<Driver> getAvailableDrivers(@RequestParam Date depart, @RequestParam Date arrive) {
        List<Driver> allDrivers = driverService.getAllDrivers();
        return driverService.availableDrivers(allDrivers, depart, arrive);
    }
    @GetMapping("/final-list")
    public List<Driver> getFinalDriverList(@RequestParam Date startDate, @RequestParam Date endDate) {
        return driverService.FinalListDriver(startDate, endDate);
    }
}
