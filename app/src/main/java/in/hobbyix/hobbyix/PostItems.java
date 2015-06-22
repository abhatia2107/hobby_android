package in.hobbyix.hobbyix;

public class PostItems{
    private String Name;
    private String ClassType;
    private String Address;
    private String Timings;
    private String Fees;
    private String BookNow;
    private String Credit;
    private  String Id;
    public PostItems(String name,String classtype,String address,String fees,String timings,String credit,String booknow,String id){
        BookNow=booknow;
        Name=name;
        ClassType=classtype;
        Address=address;
        Fees=fees;
        Timings=timings;
        Credit=credit;
        Id=id;

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
    public String getId() {
        return Id;
    }



    public String getFees() {
        return Fees;
    }
    public String getCredit() {
        return Credit;
    }



}
