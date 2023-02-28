package com.mmd_cnu.MapMyDay.model;
import org.springframework.util.Assert;

import java.util.UUID;

public class location {
    private final UUID locationId;
    private final String locationName;
    private final double latitude;
    private final double longitude;

    private int priority;

    public location(UUID locationId, String locationName, double latitude, double longitude, int priority) {

        Assert.notNull(locationName, "locationName should not be null");
        Assert.notNull(latitude, "latitude should not be null");
        Assert.notNull(longitude, "longitude should not be null");

        this.locationId = locationId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
