package in.hobbyix.hobbyix;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyOrders extends ActionBarActivity {
    private List<OrderItems> OrderList=new ArrayList<OrderItems>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        populateOrderList();
        populateOrderListView();
    }
    private void populateOrderList() {
        for(int i=1;i<=25;i++){
            OrderList.add(new OrderItems("Order Number  : "+i,"Price Per Session : Rs.100"+i+"/-","Date : 14 - 05 - 2015     Time : 10:45am"," Order Booked For Date : 17 - 05- 2015 ","Number Of Sessions: "+i,"Total Price : Rs.500"+i+"/-"));
        }
    }
    private void populateOrderListView() {
        ArrayAdapter<OrderItems> adapter=new MyListAdapter();
        ListView orderlist=(ListView)findViewById(R.id.OrdersListView);
        orderlist.setAdapter(adapter);
    }
    private class MyListAdapter extends ArrayAdapter<OrderItems>{
        public MyListAdapter(){
            super(MyOrders.this,R.layout.order_layout,OrderList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.order_layout,parent,false);
            }
            OrderItems CurrentOrder = OrderList.get(position);

            TextView OrderNumber=(TextView)itemView.findViewById(R.id.ItemOrderNumberTextView);
            OrderNumber.setText(CurrentOrder.getOrderNumber());
            TextView  PricePerSession=(TextView)itemView.findViewById(R.id.ItemPricePerSession);
            PricePerSession.setText(CurrentOrder.getPricePerSession());
            TextView DateAndTime=(TextView)itemView.findViewById(R.id.ItemBookingDate);
            DateAndTime.setText(CurrentOrder.getDateAndTime());

            TextView OrderBookedForDate=(TextView)itemView.findViewById(R.id.ItemOrderBookedForTextView);
            OrderBookedForDate.setText(CurrentOrder.getOrderBookedFor());
            TextView  NumberOfSession=(TextView)itemView.findViewById(R.id.ItemNumberOfSession);
            NumberOfSession.setText(CurrentOrder.getNumberOfSessions());
            TextView TotalPrice=(TextView)itemView.findViewById(R.id.ItemTotalPrice);
            TotalPrice.setText(CurrentOrder.getTotalPrice());
            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.MyProfile:
                Intent MyProfileIntent = new Intent(this, MyProfile.class);
                this.startActivity(MyProfileIntent);
                break;
            case R.id.ChangePassword:
                Intent ChangePasswordIntent = new Intent(this, ChangePassword.class);
                this.startActivity(ChangePasswordIntent);
                break;
            case R.id.MyOrders:
                Intent MyOrdersIntent = new Intent(this, MyOrders.class);
                this.startActivity(MyOrdersIntent);
                break;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

