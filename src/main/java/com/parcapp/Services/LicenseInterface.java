package com.parcapp.Services;

import com.parcapp.Entities.License;

import java.util.Optional;

public interface LicenseInterface {
    public License save(License entity);

    public Optional<License> findById(Integer integer);
}
