package com.parcapp.Services;
import com.parcapp.Entities.*;
import com.parcapp.Repositories.CertificateRepo;
import com.parcapp.Repositories.TripRepo;
import com.parcapp.Repositories.VehicleInsuranceRepo;
import com.parcapp.Repositories.VehicleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class VehicleService implements VehicleServiceInterface {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private VehicleInsuranceRepo insuranceRepo;
    @Autowired
    private CertificateRepo certificateRepo;
    @Autowired
    private TripRepo tripRepo;
    public  List<Vehicle> findAll(){
        return (List<Vehicle>) vehicleRepo.findAll();
    }
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        boolean isValidType = isValidVehiculeType(vehicle.getVehicleType());
        VehicleInsurance insurance = vehicle.getVehicleInsurance();
        RegistrationCertificate certificate = vehicle.getRegistrationCertificate();
        if (isValidType && insurance != null && certificate != null) {
            insuranceRepo.save(insurance);
            certificateRepo.save(certificate);
            System.out.println("le vehicule va s'enregistrer ");
            return vehicleRepo.save(vehicle);
        } else {
            throw new IllegalArgumentException("Informations de véhicule invalides.");
        }}
    @Override
    public List<String> getAllVehicleType() {
        List<String> types = new ArrayList<>();
        for (VehiculeType type : VehiculeType.values()) {
            types.add(type.name());}
        return types;}
    public static boolean isValidVehiculeType(String type) {
         for (VehiculeType vehiculeType : VehiculeType.values()) {
             if (vehiculeType.name().equalsIgnoreCase(type)) {
                 return true;
             }}
         return false;}
    @Override
    public List<Vehicle> getByVehicleType(String type){
        boolean validity = isValidVehiculeType(type);
        if (validity) {
            List<Vehicle> vehicles=(List<Vehicle>) vehicleRepo.findByVehicleType(type);
            if (vehicles != null && !vehicles.isEmpty()) {
                System.out.println("Véhicules trouvés pour le type '" + type + "':");
                for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle);}

            } else {
                System.out.println("Aucun véhicule trouvé pour le type '" + type + "'.");
            }
            return vehicles;
        } else {
            System.out.println("Type de véhicule non valide: " + type);
            return null;
        }}
    // la méthode qui retourne la situation des document d'un vehicule
    public boolean documentsStatus(int vehicleId,Date arrivalDate){
        Vehicle vehicle = vehicleRepo.findById(vehicleId).orElse(null);
        if (vehicle == null) {
            System.out.println("Le véhicule avec l'ID " + vehicleId + " n'existe pas.");
            return false;
        }
        RegistrationCertificate registrationCertificate = vehicle.getRegistrationCertificate();
        VehicleInsurance insurance = vehicle.getVehicleInsurance();
        if (registrationCertificate == null || insurance == null) {
            System.out.println("Le véhicule avec l'id "+ vehicleId + " n'a pas tous les documents nécessaires.");
            return false;
        }
        java.util.Date registrationExpiryMinus3Days = registrationCertificate.getExpiryDate();
        LocalDate insuranceExpiryMinus3Days = insurance.getExperationtDate();
        boolean isRegistrationCertificateValid = registrationExpiryMinus3Days.after(arrivalDate);
        boolean isVehicleInsuranceValid = insuranceExpiryMinus3Days.isAfter(arrivalDate.toLocalDate());
        if (isRegistrationCertificateValid && isVehicleInsuranceValid) {
            System.out.println("Tous les documents sont valides. Le véhicule " + vehicleId +" est maintenant disponible.");
            return true;
        } else {
            System.out.println("Au moins un document du véhicule " +vehicleId+"  n'est pas valide.donc n'est pas disponible.");
            return false;
        }}
    // la fonction qui filtre les vehicule qui ont des documents valide
    @Override
    public List<Vehicle> filterValidDocuments(List<Vehicle> vehicles, Date travelDate) {
        List<Vehicle> validVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (documentsStatus(vehicle.getVehicleId(), travelDate)) {
                validVehicles.add(vehicle);
                System.out.println("le vh  avec l'id :"+vehicle.getVehicleId()+" est valable");
            }}
        return validVehicles;}
    @Override
    public boolean isVehicleAvailable(Vehicle vehicle,Date depart,Date arrive) {
        List<Trip> trips = tripRepo.findByVehicle(vehicle);
        for (Trip trip : trips) {
            if (isOverlapping(trip.getDepartureDate(), trip.getArrivalDate(), depart, arrive)) {
                return false;
            }}
        return true;}
    private boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);}
    @Override
    public List<Vehicle> availableVehicles(List<Vehicle> vehicles,Date depart, Date arrive) {
        vehicles.removeIf(vehicle -> !isVehicleAvailable(vehicle, depart, arrive));
        return vehicles;}
    @Override
    public List<Vehicle> listeFinal(Date depart, Date arrive,String type) {
        List<Vehicle> veh=getByVehicleType(type);
        List<Vehicle> vehicles=availableVehicles(veh,depart,arrive);
        List<Vehicle> vehicleFiltre=filterValidDocuments(vehicles, arrive);
        return vehicleFiltre;
    }}




