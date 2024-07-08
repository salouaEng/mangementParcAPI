package com.parcapp.Controllers;
import com.parcapp.Entities.Vehicle;
import com.parcapp.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAll();
    }

    @PostMapping
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @GetMapping("/types")
    public List<String> getAllVehicleTypes() {
        return vehicleService.getAllVehicleType();
    }

    @GetMapping("/type/{type}")
    public List<Vehicle> getVehiclesByType(@PathVariable String type) {
        return vehicleService.getByVehicleType(type);
    }

    @GetMapping("/available")
    public List<Vehicle> getAvailableVehicles(@RequestParam("depart") Date depart,
                                              @RequestParam("arrive") Date arrive,
                                              @RequestParam("type") String type) {
        return vehicleService.listeFinal(depart, arrive, type);
    }

}
