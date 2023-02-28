package com.mmd_cnu.MapMyDay.model;
import java.util.List;
import java.util.UUID;
public class Route {
    private final UUID routeId;
    private location from;
    private location to;

    public Route(UUID routeId, List<location> locations, location from, location to) {
        this.routeId = routeId;
        this.from = from;
        this.to = to;
    }
}
