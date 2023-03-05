package app.edufindermadrid.entities;


import com.google.gson.annotations.SerializedName;

public class EduCenter {
    @SerializedName("@id")
    private String id;
    private String title;
    private Location location;
    private Organization organization;

    public String getTitle() {
        return title;
    }
}
