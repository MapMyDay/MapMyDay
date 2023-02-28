package com.mmd_cnu.MapMyDay.model;

import java.util.List;
import java.util.UUID;

public class MapData {
        private final UUID mapId;
        private final UUID userId;
    private MapStatus mapStatus;
    private List<location> locations;

    public MapData(UUID mapId, UUID userId, List<location> locations) {
        this.mapId = mapId;
        this.userId = userId;
        this.mapStatus = MapStatus.INCOMPLETE;
        this.locations = locations;
    }

    public MapData(UUID mapId, UUID userId, MapStatus mapStatus) {
        this.mapId = mapId;
        this.userId = userId;
        this.mapStatus = mapStatus;
    }

    public MapData(UUID mapId, UUID userId) {
        this.mapId = mapId;
        this.userId = userId;
        this.mapStatus = MapStatus.INCOMPLETE;
    }

    public void setMapStatus(MapStatus mapStatus) {
        this.mapStatus = mapStatus;
    }

    public MapStatus getMapStatus() {
        return mapStatus;
    }

    public List<location> getLocations() {
        return locations;
    }

    public void setLocations(List<location> locations) {
        this.locations = locations;
    }

    public UUID getMapId() {
        return mapId;
    }

    public UUID getUserId() {
        return userId;
    }


}
