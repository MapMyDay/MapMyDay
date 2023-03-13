package com.mmd_cnu.MapMyDay.repository;

import com.mmd_cnu.MapMyDay.model.Location;
import com.mmd_cnu.MapMyDay.model.MapData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepository {
    List<Location> findAll();
    Location insert(Location location);
    Location update(Location location);
    void delete(Location location);
    void deleteAll();
    public Optional<Location> findByMapId(UUID locationId);
}
