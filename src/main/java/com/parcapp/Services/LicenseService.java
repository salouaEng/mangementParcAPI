package com.parcapp.Services;

import com.parcapp.Entities.License;
import com.parcapp.Entities.LicenseType;
import com.parcapp.Repositories.LicenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LicenseService  implements LicenseInterface{
    @Autowired
    private LicenseRepo licenseRepo;
    public static boolean isValidLicenseType(String type) {
        for (LicenseType licenseType : LicenseType.values()) {
            if(licenseType.name().equalsIgnoreCase(type)) {
                return true;
            }}
        return false;}
    public License save(License entity){
    boolean isvalide=isValidLicenseType(entity.getLicenseType());
    if(isvalide){
        return licenseRepo.save(entity);}
     else {
        throw new IllegalArgumentException("Informations de véhicule invalides.");
    }}


    public Iterable<License> saveAll(Iterable<License> entities) {
        return licenseRepo.saveAll(entities);
    }

    public Optional<License> findById(Integer integer) {
        return licenseRepo.findById(integer);
    }




}
