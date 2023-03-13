package com.mmd_cnu.MapMyDay.service;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LocationService {
    List<Location> getAllLocations(Location location);
    List<Location> createLocation(Location location);
    void deleteLocation(UUID location_id);
    MapData updateLocation(Location updatedLocation);
}
