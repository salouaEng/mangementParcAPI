package com.parcapp.Services;

import com.parcapp.Entities.RegistrationCertificate;
import com.parcapp.Repositories.CertificateRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class CertificateService implements  CertificateInterface{
    @Autowired
    private CertificateRepo c;
    @Override
    public RegistrationCertificate save(RegistrationCertificate cetificate) {
        return c.save(cetificate);
    }
    @Override
    public List<RegistrationCertificate> getAllCertificate() {

        return (List<RegistrationCertificate>) c.findAll();
    }
    @Override
    public Optional<RegistrationCertificate> findById(int id) {
        return c.findById(id);
    }

}
