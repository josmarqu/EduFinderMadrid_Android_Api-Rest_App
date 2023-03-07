package app.edufindermadrid.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EduOrganization  implements Parcelable {
    @SerializedName("@graph")
    private List<Organization> graph;

    protected EduOrganization(Parcel in) {
    }

    public static final Creator<EduOrganization> CREATOR = new Creator<EduOrganization>() {
        @Override
        public EduOrganization createFromParcel(Parcel in) {
            return new EduOrganization(in);
        }

        @Override
        public EduOrganization[] newArray(int size) {
            return new EduOrganization[size];
        }
    };

    public Organization getGraph() {
        return  graph.get(0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Organization {
        @SerializedName("organization")
        private OrganizationDetails organizationDetails;

        public OrganizationDetails getOrganizationDetails() {
            return organizationDetails;
        }
    }

    public static class OrganizationDetails {
        @SerializedName("organization-desc")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}