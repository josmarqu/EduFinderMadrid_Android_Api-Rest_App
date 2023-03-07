package app.edufindermadrid.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EduCenterList implements Parcelable {
    @SerializedName("@graph")
    private List<EduCenter> eduCenters;

    protected EduCenterList(Parcel in) {
        eduCenters = in.createTypedArrayList(EduCenter.CREATOR);
    }

    public static final Creator<EduCenterList> CREATOR = new Creator<EduCenterList>() {
        @Override
        public EduCenterList createFromParcel(Parcel in) {
            return new EduCenterList(in);
        }

        @Override
        public EduCenterList[] newArray(int size) {
            return new EduCenterList[size];
        }
    };

    public List<EduCenter> getEduCenters() {
        return eduCenters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(eduCenters);
    }
}