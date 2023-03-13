package com.mmd_cnu.MapMyDay.model;
import org.springframework.util.Assert;

import java.util.UUID;

public class Location {
    private final UUID locationId;
    private final String locationName;
    private final double latitude;
    private final double longitude;

    public Location(UUID locationId, String locationName, double latitude, double longitude) {

        Assert.notNull(locationName, "locationName should not be null");
        Assert.notNull(latitude, "latitude should not be null");
        Assert.notNull(longitude, "longitude should not be null");

        this.locationId = locationId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
