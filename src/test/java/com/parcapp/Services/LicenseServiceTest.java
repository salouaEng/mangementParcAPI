package com.parcapp.Services;
import com.parcapp.Entities.License;
import com.parcapp.Entities.LicenseType;
import com.parcapp.Entities.LicenseTypeEntity;
import com.parcapp.Repositories.LicenseRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static com.parcapp.Entities.LicenseType.TYPE_A;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LicenseServiceTest {
    LicenseTypeService licenseType;
    @Autowired
    LicenseTypeService licenseTypeService; // Correction du nom de la variable

    @Autowired
    LicenseService licenseService;

    //@Test
    /*void save() {
        LicenseTypeEntity type = new LicenseTypeEntity();
        type.setLicenceType(LicenseType.TYPE_A);
        LicenseTypeEntity savedLicenseType = licenseTypeService.saveLicenseType(type); // Utilisation de la variable correcte
        assertNotNull(savedLicenseType);
        License license = new License();
        license.setLicenseType(savedLicenseType);
        LocalDate expirationDate = LocalDate.now().plusDays(120);
        LocalDate issuanceDate = LocalDate.now();
        license.setExpirationDate(expirationDate);
        license.setIssuanceDate(issuanceDate);
        License savedLicense = licenseService.save(license);
        assertNotNull(savedLicense);
    }*/
    @Test
    void findByID() {
        Optional<Optional<License>> retrievedLicenseOptional = Optional.ofNullable(licenseService.findById(7));
        assertTrue(retrievedLicenseOptional.isPresent());
        LicenseType licenceType;
         }




}
