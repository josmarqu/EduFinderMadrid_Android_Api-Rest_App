package app.edufindermadrid.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class EduCenter implements Parcelable {
    @SerializedName("@id")
    public String idJson;
    @SerializedName("title")
    public String title;
    public Location location;

    protected EduCenter(Parcel in) {
        idJson = in.readString();
        title = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<EduCenter> CREATOR = new Creator<EduCenter>() {
        @Override
        public EduCenter createFromParcel(Parcel in) {
            return new EduCenter(in);
        }

        @Override
        public EduCenter[] newArray(int size) {
            return new EduCenter[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getIdJson() {
        return idJson;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idJson);
        parcel.writeString(title);
    }
}
