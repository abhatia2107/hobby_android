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
    public PostItems(String name,String classType,String address,String fees,String timings,String credit,String bookNow,String id){
        BookNow=bookNow;
        Name=name;
        ClassType=classType;
        Address=address;
        Fees="Rs." + fees;
        Timings=timings;
        Credit=credit;
        Id=id;
        if(Timings.length()>20){
            Timings = Timings.substring(0,20)+"...";
        }
        if(Address.length()>20){
            Address = Address.substring(0,20)+"...";
        }
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
