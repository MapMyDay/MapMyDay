package com.mmd_cnu.MapMyDay.repository;

import com.mmd_cnu.MapMyDay.model.Location;
import com.mmd_cnu.MapMyDay.model.MapData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MapRepository {
    List<MapData> findAll();
    MapData insert(MapData map);
    MapData update(MapData map);
    void delete(MapData map);
    void deleteAll();
    public Optional<MapData> findByMapId(UUID mapId);
    public List<MapData> findByUserId(UUID userId);
    MapData insertLocation(MapData map, Location location);
}
