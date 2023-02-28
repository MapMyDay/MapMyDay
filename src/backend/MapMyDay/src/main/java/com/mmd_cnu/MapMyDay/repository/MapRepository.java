package com.mmd_cnu.MapMyDay.repository;

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
    public Optional<MapData> findByMapId(UUID mapId);
    public List<MapData> findByUserId(UUID userId);
}
