package com.parcapp.Services;

import com.parcapp.Entities.LicenseTypeEntity;

import java.util.List;
import java.util.Optional;

public interface LicenseTypeInterface {
    List<LicenseTypeEntity> getAllLicenseType();
    Optional<LicenseTypeEntity> getLicenseById(Long id);
    LicenseTypeEntity saveLicenseType(LicenseTypeEntity licneseType);
}
