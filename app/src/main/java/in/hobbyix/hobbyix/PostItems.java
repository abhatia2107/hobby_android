package in.hobbyix.hobbyix;

public class PostItems{
    private String Name;
    private String ClassType;
    private String Address;
    private String Timings;
    private String Fees;
    private String Credit;
    private String BookNow;
<<<<<<< HEAD
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
=======
>>>>>>> e505c5ff76a83514ecedc0f2986a1d5936e9a959

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
<<<<<<< HEAD


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



=======
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
>>>>>>> e505c5ff76a83514ecedc0f2986a1d5936e9a959
}
