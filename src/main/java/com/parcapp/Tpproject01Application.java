package com.parcapp;

import com.parcapp.Entities.Driver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
@SpringBootApplication
public class Tpproject01Application implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(Tpproject01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }


    //@Override
   // public void run(String... args) throws Exception {
        //List<Driver> dispoDrivers = driverService.getAllDrivers();

       // for (Driver driver : dispoDrivers) {
        //    System.out.println(driver);
       // }

    //}
}

