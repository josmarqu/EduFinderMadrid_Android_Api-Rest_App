package app.edufindermadrid.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EduCenterList {
    @SerializedName("@graph")
    private List<EduCenter> eduCenters;

    public List<EduCenter> getEduCenters() {
        return eduCenters;
    }
}
