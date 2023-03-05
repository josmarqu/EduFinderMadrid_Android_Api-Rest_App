package app.edufindermadrid.entities;


import android.location.Address;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EduCenter implements Serializable {
    @SerializedName("@id")
    private String id;
    private String title;
    private Location location;
    private Organization organization;

    public String getTitle() {
        return title;
    }

    public Location getLocation() {
        return location;
    }
}
