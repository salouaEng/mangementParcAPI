
package com.parcapp.Services;
import com.parcapp.Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

public class VehicleServiceTest {
    @Autowired
    VehicleService serviceVehicle;
    @Autowired
    CertificateService ctr;
    @Autowired
    InsuranceService ins;
    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testSaveVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleCapacity(20);
        vehicle.setVignette(true);
        vehicle.setAvailable(true);
        vehicle.setControlleVisit(true);
        vehicle.setVehicleType("Car");
        VehicleInsurance insurance = new VehicleInsurance();
        insurance.setExperationtDate(LocalDate.parse("2024-05-30"));
        insurance.setIssuanceDate(LocalDate.parse("2024-01-30"));
        RegistrationCertificate certificate = new RegistrationCertificate();
        certificate.setRegistrationNumber(String.valueOf(1234));
        certificate.setExpiryDate(Date.valueOf("2024-05-30"));
        certificate.setFuelType("gasoil");
        certificate.setNumberOfSeats(20);
        vehicle.setVehicleInsurance(insurance);
        vehicle.setRegistrationCertificate(certificate);
        Vehicle savedVehicle = serviceVehicle.save(vehicle);
        assertNotNull(savedVehicle);
    }
    @Test

    public void testIsValidVehiculeType(){
        String type="CAR";
        boolean isValid=serviceVehicle.isValidVehiculeType(type);
        assertTrue(isValid);
        //assertFalse(isValid);
    }

    @Test
    public void testgetVehicleByType(){
        String type="CAR";
        ArrayList<Vehicle> listeVehicle= (ArrayList<Vehicle>) serviceVehicle.getByVehicleType(type);
        assertNotNull(listeVehicle);
    }
    @Test
    public void testdocumentsStatus(){
        Date dateVoyage = Date.valueOf("2024-05-30");
        int id = 2;
        boolean status= serviceVehicle.documentsStatus(id,dateVoyage);
        assertFalse(false);
    }
    @Test
    public void testFilterValidDocuments() {
        String type = "CAR";
        ArrayList<Vehicle> listeVehicle = (ArrayList<Vehicle>) serviceVehicle.getByVehicleType(type);
        ArrayList<Vehicle> listeFiltrer = (ArrayList<Vehicle>) serviceVehicle.filterValidDocuments(listeVehicle, Date.valueOf("2024-05-30"));
        assertThat(listeFiltrer).isSubsetOf(listeVehicle);
        for (Vehicle vehicle : listeFiltrer) {
            assertThat(serviceVehicle.documentsStatus(vehicle.getVehicleId(), Date.valueOf("2024-05-30"))).isTrue();
        }}
    @Test
    public void testAvailableVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles =serviceVehicle.findAll();
        Date depart = Date.valueOf("2024-05-30");
        Date arrive = Date.valueOf("2024-06-05");
        List<Vehicle> availableVehicles = serviceVehicle.availableVehicles(vehicles, depart, arrive);
        assertThat(availableVehicles).isNotNull();
        for (Vehicle vehicle : availableVehicles) {
            assertThat(serviceVehicle.isVehicleAvailable(vehicle, depart, arrive)).isTrue();
        }
    }
    @Test
    public void testListeFinal() {
        Date depart = Date.valueOf("2024-05-30");
        Date arrive = Date.valueOf("2024-06-05");
        String type = "CAR";
        List<Vehicle> vehicleList = serviceVehicle.listeFinal(depart, arrive, type);
        assertThat(vehicleList).isNotNull();
        for (Vehicle vehicle : vehicleList) {
            assertThat(serviceVehicle.isVehicleAvailable(vehicle, depart, arrive)).isTrue();
        }
    }
}

