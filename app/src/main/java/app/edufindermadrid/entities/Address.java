package app.edufindermadrid.entities;

import com.google.gson.annotations.SerializedName;

public class Address {
    private District district;
    private Area area;
    private String locality;
    @SerializedName("postal_code")
    private String postalCode;
    @SerializedName("street_address")
    private String streetAddress;

    // TODO: Getters and Setters
}
