package com.parcapp.Services;

import com.parcapp.Entities.LicenseType;
import com.parcapp.Entities.LicenseTypeEntity;
import com.parcapp.Repositories.LicenseTypeEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LicenseTypeService implements LicenseTypeInterface {

    @Autowired
private LicenseTypeEntityRepo repo;
    @Override
    public List<LicenseTypeEntity> getAllLicenseType() {

        return (List<LicenseTypeEntity>) repo.findAll();
    }
    @Override
    public Optional<LicenseTypeEntity> getLicenseById(Long id) {
        Optional<LicenseTypeEntity> licenseType =repo.findById(id);
        if(licenseType.isPresent())
            return licenseType;
        return Optional.empty();
    }
    @Override
    public LicenseTypeEntity saveLicenseType(LicenseTypeEntity licenseType) {
        boolean isValid = false;
        for (LicenseType enumValue : LicenseType.values()) {
            if (enumValue.name().equals(licenseType.getLicenceType().name())) {
                isValid = true;
                break;
            }}
        if (isValid) {
            return repo.save(licenseType);
        } else {
            throw new IllegalArgumentException("Le type de licence n'est pas valide.");
        }
    }


}
