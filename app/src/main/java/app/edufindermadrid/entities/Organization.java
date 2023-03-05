package app.edufindermadrid.entities;

import com.google.gson.annotations.SerializedName;

public class Organization {
    @SerializedName("organization-desc")
    private String description;
    @SerializedName("accesibility")
    private String accessibility;
    @SerializedName("schedule")
    private String schedule;
    @SerializedName("services")
    private String services;
    @SerializedName("organization-name")
    private String organizationName;

    // TODO: Getters and Setters
}
