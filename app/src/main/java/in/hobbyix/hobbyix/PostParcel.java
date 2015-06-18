package in.hobbyix.hobbyix;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yashdeep Sharma on 18-06-2015.
 */
public class PostParcel implements Parcelable {
    private static final String KEY_INSTITUTE_NAME = "InstituteName";
    private static final String KEY_CLASS_TYPE = "ClassType";
    private static final String KEY_ADDRESS = "Address";
    private static final String KEY_TIMINGS = "Timings";
    private static final String KEY_FEES  = "Fees";
    private String InstituteName;
    private String ClassType;
    private String Address;
    private String Timings;
    private String Fees;
    public PostParcel(){

    }

    public PostParcel(String instituteName, String classType, String address, String timings, String fees) {
        InstituteName = instituteName;
        ClassType = classType;
        Address = address;
        Timings = timings;
        Fees = fees;
    }

    public String getInstituteName() {
        return InstituteName;
    }

    public void setInstituteName(String instituteName) {
        InstituteName = instituteName;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String classType) {
        ClassType = classType;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTimings() {
        return Timings;
    }

    public void setTimings(String timings) {
        Timings = timings;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        // insert the key value pairs to the bundle
        bundle.putString(KEY_INSTITUTE_NAME, InstituteName);
        bundle.putString(KEY_ADDRESS, Address);
        bundle.putString(KEY_CLASS_TYPE,ClassType);
        bundle.putString(KEY_TIMINGS,Timings);
        bundle.putString(KEY_FEES,Fees);
        // write the key value pairs to the parcel
        dest.writeBundle(bundle);

    }
    /**
     * Creator required for class implementing the parcelable interface.
     */
    public static final Parcelable.Creator<PostParcel> CREATOR = new Creator<PostParcel>() {

        @Override
        public PostParcel createFromParcel(Parcel source) {
            // read the bundle containing key value pairs from the parcel
            Bundle bundle = source.readBundle();

            // instantiate a person using values from the bundle
            return new PostParcel(bundle.getString(KEY_INSTITUTE_NAME),bundle.getString(KEY_CLASS_TYPE),bundle.getString(KEY_ADDRESS),bundle.getString(KEY_TIMINGS),bundle.getString(KEY_FEES));
        }

        @Override
        public PostParcel[] newArray(int size) {
            return new PostParcel[size];
        }
    };
}
