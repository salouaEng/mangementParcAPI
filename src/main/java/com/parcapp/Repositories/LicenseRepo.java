package com.parcapp.Repositories;

import com.parcapp.Entities.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LicenseRepo extends CrudRepository<License, Integer> {
}
