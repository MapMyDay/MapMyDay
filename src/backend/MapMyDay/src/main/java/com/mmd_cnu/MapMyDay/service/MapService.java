package com.mmd_cnu.MapMyDay.service;

import com.mmd_cnu.MapMyDay.model.MapData;
import com.mmd_cnu.MapMyDay.model.MapStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MapService {
    List<MapData> getMapsByUserId(UUID userId);
    List<MapData> getAllMaps();
    MapData createMap();
    MapData createMap(MapStatus mapStatus);

}
