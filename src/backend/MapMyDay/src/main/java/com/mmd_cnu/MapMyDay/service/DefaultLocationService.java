package com.mmd_cnu.MapMyDay.service;

import com.mmd_cnu.MapMyDay.model.Location;
import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.repository.MapRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DefaultLocationService implements LocationService {
    @Override
    public List<Location> getAllLocations(Location location) {
        return null;
    }

    @Override
    public List<Location> createLocation(Location location) {
        return null;
    }

    @Override
    public void deleteLocation(UUID location_id) {

    }

    @Override
    public MapData updateLocation(Location updatedLocation) {
        return null;
    }
//    private final MapRepository mapRepository;
//
//    public DefaultMapService(MapRepository mapRepository) {
//        this.mapRepository = mapRepository;
//    }
//
//    @Override
//    public List<MapData> getMapsByUserId(UUID userId) {
//        return mapRepository.findByUserId(userId);
//    }
//
//    @Override
//    public List<MapData> getAllMaps() {
//        return mapRepository.findAll();
//    }
//
//    @Override
//    public MapData createMap(UUID user_id) {
//        var map = new MapData(UUID.randomUUID(), user_id);
//        return mapRepository.insert(map);
//    }
//
//    @Override
//    public void deleteMap(UUID map_id) {
//        Optional<MapData> map = mapRepository.findByMapId(map_id);
//        if(!map.isEmpty()) {
//            mapRepository.delete(map.get());
//        }
//    }
//
//    @Override
//    public MapData updateMap(MapData updatedMap) {
//        return mapRepository.update(updatedMap);
//    }
}
