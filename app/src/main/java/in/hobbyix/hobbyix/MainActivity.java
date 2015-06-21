package in.hobbyix.hobbyix;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity  {

    ListView postItemListView;
    private List<PostItems> PostItemsList = new ArrayList<PostItems>();

    private ProgressDialog pDialog;
    //url to get required guideline
    private static String url_for_institute = "http://hobbyix.com/json/filter";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_INSTITUTE = "institute";
    public static String[] subcategory_filter=new String[100];
    static int sub_length=0;
    static int local_length=0;
    public static String[] locality_filter=new String[100];
    static String message = null;
    //object for JSONParser class
    static JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values

    ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();
    //JSONArray(inbuilt) to extract the JSONArray
    static JSONArray guidelines = null;
    static String[][] institute_list=new String[100][100];

    //  SessionManagement session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populatePostItemsList();
        populatePostItemsListView();

    }

    private void populatePostItemsList() {
        for(int i=0;i<10;i++){
            PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
        }
    }

    private void populatePostItemsListView() {
        ArrayAdapter<PostItems> adapter = new PostItemListAdapter();
        ListView PostItemsListView = (ListView)findViewById(R.id.PostItemListView);
        PostItemsListView.setAdapter(adapter);
    }
    private class PostItemListAdapter extends  ArrayAdapter<PostItems>{

        public PostItemListAdapter() {
            super(MainActivity.this, R.layout.item_view);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }
            PostItems CurrentPost = PostItemsList.get(position);

            TextView InstituteName = (TextView)findViewById(R.id.item_NameTextView);
            TextView InstituteClassType = (TextView)findViewById(R.id.item_ClassTypeTextView);
            TextView InstituteTimings = (TextView)findViewById(R.id.item_TimingsTextView);
            TextView InstituteAddress = (TextView)findViewById(R.id.item_AddressTextView);
            TextView InstituteFees = (TextView)findViewById(R.id.item_FessTextView);
            TextView InstituteCredit = (TextView)findViewById(R.id.CreditTextView);
            Button BookNowButton = (Button)findViewById(R.id.item_book_now_button);

            InstituteName.setText(CurrentPost.getName());
            InstituteClassType.setText(CurrentPost.getClassType());
            InstituteTimings.setText(CurrentPost.getTimings());
            InstituteAddress.setText(CurrentPost.getAddress());
            InstituteFees.setText(CurrentPost.getFees());
            InstituteCredit.setText(CurrentPost.getCredit());
            BookNowButton.setText(CurrentPost.getBookNow());
            InstituteName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });
            InstituteClassType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });
            InstituteTimings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });
            InstituteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });
            InstituteFees.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });
            InstituteCredit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });
            BookNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this,SamplePage.class);
                    startActivity(BookNowSamplePageIntent);
                }
            });

            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu( final Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
       /* searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide action item
                if (menu != null) {
                    menu.findItem(R.id.action_login).setVisible(false);
                    menu.findItem(R.id.action_filter).setVisible(false);
                }

            }
        });*/
       /*searchView .setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
              //  adapter.getFilter().filter("");
                // re-show the action button
                if (menu != null) {

                    menu.findItem(R.id.action_filter).setVisible(true);
                    menu.findItem(R.id.action_login).setVisible(true);
                    menu.findItem(R.id.action_filter).setVisible(true);

                }
                return false;

            }
        });*/

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText)
            {
                // this is your adapter that will be filtered
                // myAdapter.getFilter().filter(newText);
                //System.out.println("on text chnge text: "+newText);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Search_Backend.get_search(query);
                // this is your adapter that will be filtered
                // myAdapter.getFilter().filter(query);


                System.out.println("on query submit: "+query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);


        return true;

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