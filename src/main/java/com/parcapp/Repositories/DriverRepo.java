package com.parcapp.Repositories;
import com.parcapp.Entities.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepo extends CrudRepository<Driver,Double> {

}
