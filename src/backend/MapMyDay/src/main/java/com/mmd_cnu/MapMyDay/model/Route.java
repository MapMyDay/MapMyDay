package com.mmd_cnu.MapMyDay.model;
import java.util.List;
import java.util.UUID;
public class Route {
    private final UUID routeId;
    private Location from;
    private Location to;

    private int busCount;
    private int subwayBusCount;


    public Route(UUID routeId, List<Location> locations, Location from, Location to) {
        this.routeId = routeId;
        this.from = from;
        this.to = to;
    }
}
