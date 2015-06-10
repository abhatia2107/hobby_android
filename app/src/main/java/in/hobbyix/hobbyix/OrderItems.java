package in.hobbyix.hobbyix;


/**
 * Created by Yashdeep Sharma on 09-06-2015.
 */
public class OrderItems {
    private String OrderNumber;
    private String PricePerSession;
    private String DateAndTime;
    private String OrderBookedFor;
    private String NumberOfSessions;
    private String TotalPrice;

    public OrderItems(String orderNumber, String pricePerSession, String dateAndTime, String orderBookedFor, String numberOfSessions, String totalPrice) {
        OrderNumber = orderNumber;
        PricePerSession = pricePerSession;
        DateAndTime = dateAndTime;
        OrderBookedFor = orderBookedFor;
        NumberOfSessions = numberOfSessions;
        TotalPrice = totalPrice;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public String getPricePerSession() {
        return PricePerSession;
    }

    public String getDateAndTime() {
        return DateAndTime;
    }

    public String getOrderBookedFor() {
        return OrderBookedFor;
    }

    public String getNumberOfSessions() {
        return NumberOfSessions;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }
}
