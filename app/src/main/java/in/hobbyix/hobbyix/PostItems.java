package in.hobbyix.hobbyix;

public class PostItems{
    private String Name;
    private String ClassType;
    private String Address;
    private String Timings;
    private String Fees;
    private String Credit;
    private String BookNow;

    public PostItems(String name, String classType, String address, String timings, String fees,String credit, String bookNow) {
        Name = name;
        ClassType = classType;
        Address = address;
        Timings = timings;
        Fees = fees;
        BookNow = bookNow;
        Credit = credit;
    }

    public String getBookNow() {
        return BookNow;
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

    public String getCredit() {
        return Credit;
    }
}
