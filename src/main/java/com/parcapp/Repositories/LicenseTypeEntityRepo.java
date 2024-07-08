package com.parcapp.Repositories;

import com.parcapp.Entities.LicenseTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseTypeEntityRepo  extends CrudRepository<LicenseTypeEntity, Long> {

}
