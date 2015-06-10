package in.hobbyix.hobbyix;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private List<PostItems> PostList=new ArrayList<PostItems>();
    public ArrayList<HashMap<String, String>> list= new ArrayList<HashMap<String, String>>();
    public  String list_institute[][]= new String[100][6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("dhfjkd", "kjlkjkjklllkl");
        list_institute= new Institute_Details().store_details();
        Log.e("dhfjkddddddd",""+list_institute[0][0]);
        populatePostList();
        populatePostListView();
        registerClickCallback();
    }
    private void populatePostList() {
        Log.e("f", "" + list_institute[0][0]);
        // list_institute=send_details(list_institute);
        //list=store(list);
        // Log.e("list", "" + list + "");
        // int size=list.size();
        for (int i = 0; i < 25; i++) {
            String x = list_institute[i][0];
            String y = list_institute[i][1];
            String z = list_institute[i][2];
            String a = list_institute[i][3];
            // Log.e("djfk",""+list.get(i).get("institute")+""+list.get(i).get("batch_category")+""+list.get(i).get("venue_address"));
            PostList.add(new PostItems(x, y, z, "Mon-Sat:5-6pm", "₹ " + a + " / Session", R.id.item_book_now_button));
        }
    }
    private void populatePostListView() {
        ArrayAdapter<PostItems> adapter=new MyListAdapter();
        ListView postlist=(ListView)findViewById(R.id.listView);
        postlist.setAdapter(adapter);
    }
    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Intent appInfo = new Intent(viewClicked.getContext(), SamplePage.class);
                startActivity(appInfo);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<PostItems>{
        public MyListAdapter(){
            super(MainActivity.this,R.layout.item_view,PostList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }
            PostItems CurrentPost = PostList.get(position);
            TextView InstituteName=(TextView)itemView.findViewById(R.id.item_NameTextView);
            InstituteName.setText(CurrentPost.getName());
            InstituteName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Payment = new Intent(v.getContext(),SamplePage.class);
                    startActivityForResult(Payment,0);
                }
            });

            TextView ClassType=(TextView)itemView.findViewById(R.id.item_ClassTypeTextView);
            ClassType.setText(CurrentPost.getClassType());

            TextView InstituteAddress=(TextView)itemView.findViewById(R.id.item_AddressTextView);
            InstituteAddress.setText(CurrentPost.getAddress());

            TextView InstituteTimings=(TextView)itemView.findViewById(R.id.item_TimingsTextView);
            InstituteTimings.setText(CurrentPost.getTimings());

            TextView InstituteFees=(TextView)itemView.findViewById(R.id.item_FessTextView);
            InstituteFees.setText(CurrentPost.getFees());

            Button BookNow=(Button)itemView.findViewById(R.id.item_book_now_button);
            BookNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Payment = new Intent(v.getContext(),SamplePage.class);
                    startActivityForResult(Payment,0);
                }
            });

            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_filter:
                Intent FilterIntent = new Intent(this, FilterPage.class);
                this.startActivity(FilterIntent);
                break;
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
            case R.id.action_login:
                Intent LoginIntent = new Intent(this, LoginActivity.class);
                this.startActivity(LoginIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}