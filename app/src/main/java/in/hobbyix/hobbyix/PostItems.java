package in.hobbyix.hobbyix;

import android.widget.Button;

public class PostItems {
    private String Name;
    private String ClassType;
    private String Address;
    private String Timings;
    private String Fees;
    private int ButttonId;
    public PostItems(String name, String classType, String address, String timings, String fees,int  buttonid) {
        Name = name;
        ClassType = classType;
        Address = address;
        Timings = timings;
        Fees = fees;
        ButttonId=buttonid;
    }

    public String getName() {
        return Name;
    }

    public String getClassType() {
        return ClassType;
    }

    public String getAddress() {
        return Address;
    }

    public String getTimings() {
        return Timings;
    }

    public String getFees() {
        return Fees;
    }

    public int getButttonId() {return  ButttonId; }
}
