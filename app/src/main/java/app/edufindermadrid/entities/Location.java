package app.edufindermadrid.entities;

import java.io.Serializable;

public class Location implements Serializable {
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
