package app.edufindermadrid.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EduCenterList implements Serializable {
    @SerializedName("@graph")
    private List<EduCenter> eduCenters;

    public List<EduCenter> getEduCenters() {
        return eduCenters;
    }
}
