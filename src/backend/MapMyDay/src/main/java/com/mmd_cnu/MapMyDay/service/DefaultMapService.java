package com.mmd_cnu.MapMyDay.service;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import com.mmd_cnu.MapMyDay.repository.MapRepository;

import java.util.List;
import java.util.UUID;

public class DefaultMapService implements MapService{
    private final MapRepository mapRepository;

    public DefaultMapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Override
    public List<MapData> getMapsByUserId(UUID userId) {
        return mapRepository.findByUserId(userId);
    }

    @Override
    public List<MapData> getAllMaps() {
        return mapRepository.findAll();
    }

    @Override
    public MapData createMap() {
        var map = new MapData(UUID.randomUUID(), UUID.randomUUID());
        return mapRepository.insert(map);
    }

    @Override
    public MapData createMap(MapStatus mapStatus) {
        var map = new MapData(UUID.randomUUID(), UUID.randomUUID(), mapStatus);
        return mapRepository.insert(map);
    }
}
