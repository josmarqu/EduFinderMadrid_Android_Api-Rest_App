package app.edufindermadrid.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EduDetails implements Parcelable {
    @SerializedName("@graph")
    private List<Graph> graph;

    protected EduDetails(Parcel in) {
    }

    public static final Creator<EduDetails> CREATOR = new Creator<EduDetails>() {
        @Override
        public EduDetails createFromParcel(Parcel in) {
            return new EduDetails(in);
        }

        @Override
        public EduDetails[] newArray(int size) {
            return new EduDetails[size];
        }
    };

    public Graph getGraph() {
        return  graph.get(0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class Graph {
        @SerializedName("organization")
        private OrganizationDetails organizationDetails;
        private Address address;
        private String title;
        public OrganizationDetails getOrganizationDetails() {
            return organizationDetails;
        }
        public Address getAddress() {
            return address;
        }
        public String getTitle() {
            return title;
        }
    }

    public static class OrganizationDetails {
        @SerializedName("organization-desc")
        private String description;

        public String getDescription() {
            return description;
        }
    }

    public static class Address {
        private String locality;
        @SerializedName("postal-code")
        private String postalCode;
        @SerializedName("street-address")
        private String streetAddress;
        public String getLocality() {
            return locality;
        }
        public String getPostalCode() {
            return postalCode;
        }
        public String getStreetAddress() {
            return streetAddress;
        }
    }
}