package com.parcapp.Repositories;

import com.parcapp.Entities.RegistrationCertificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepo extends CrudRepository<RegistrationCertificate,Integer> {

}
