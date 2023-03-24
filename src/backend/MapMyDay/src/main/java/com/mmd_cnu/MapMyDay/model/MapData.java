package com.mmd_cnu.MapMyDay.model;

import java.util.List;
import java.util.UUID;

public class MapData {
        private final UUID mapId;
        private final UUID userId;
    private MapStatus mapStatus;

    private List<Location> locations;
    private List<Route> routes;

    public MapData(UUID mapId, UUID userId) {
        this.mapId = mapId;
        this.userId = userId;
        this.mapStatus = MapStatus.INCOMPLETE;
    }

    public MapData(UUID mapId, UUID userId, MapStatus mapStatus) {
        this.mapId = mapId;
        this.userId = userId;
        this.mapStatus = mapStatus;
    }

    public void setMapStatus(MapStatus mapStatus) {
        this.mapStatus = mapStatus;
    }

    public MapStatus getMapStatus() {
        return mapStatus;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public UUID getMapId() {
        return mapId;
    }

    public UUID getUserId() {
        return userId;
    }


}
