package com.parcapp.Services;

import com.parcapp.Entities.RegistrationCertificate;

import java.util.List;
import java.util.Optional;

public interface CertificateInterface {
    public RegistrationCertificate save(RegistrationCertificate c);
    public List<RegistrationCertificate> getAllCertificate();
    public Optional<RegistrationCertificate> findById(int id);
}
