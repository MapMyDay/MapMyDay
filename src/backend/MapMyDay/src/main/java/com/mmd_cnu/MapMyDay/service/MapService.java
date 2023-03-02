package com.mmd_cnu.MapMyDay.service;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;

import java.util.List;
import java.util.UUID;

public interface MapService {
    List<MapData> getMapsByUserId(UUID userId);
    List<MapData> getAllMaps();
    MapData createMap();
    MapData createMap(MapStatus mapStatus);

}
