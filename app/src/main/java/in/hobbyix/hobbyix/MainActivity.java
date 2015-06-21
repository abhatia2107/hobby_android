package in.hobbyix.hobbyix;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
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
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    static ListView postItemListView;
    static ArrayList<PostItems> postItemArrayList;
    //the progessdialog for progress bar
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // session = new SessionManagement(getApplicationContext());
        postItemListView = (ListView)findViewById(R.id.listView);
        postItemArrayList = new ArrayList<>();
       // session.checkLogin();
        new PostItemsAsyncTask().execute();
    }

    @Override
    public void onClick(View v) {

    }
    class InstituteAdapter extends ArrayAdapter<PostItems> {

         class ViewHolder{
            public TextView institute_name;
            public TextView institute_class_type;
            public TextView institute_address;
            public TextView institute_timing;
            public TextView institute_price;
            public Button book_now;
         }

        ArrayList<PostItems> ArrayListPost;
        int resource;
        Context context;
        LayoutInflater layoutInflater;

        public InstituteAdapter(Context context,int resource,ArrayList<PostItems> objects){
            super(context,resource,objects);

            ArrayListPost = objects;
            this.resource = resource;
            this.context=context;

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder ;
            if(convertView == null){
                convertView = layoutInflater.inflate(resource,null);
                viewHolder = new ViewHolder();
                viewHolder.book_now = (Button)convertView.findViewById(R.id.item_book_now_button);
                viewHolder.institute_name = (TextView)convertView.findViewById(R.id.item_NameTextView);
                viewHolder.institute_class_type = (TextView)convertView.findViewById(R.id.item_ClassTypeTextView);
                viewHolder.institute_timing = (TextView)convertView.findViewById(R.id.item_TimingsTextView);
                viewHolder.institute_price = (TextView)convertView.findViewById(R.id.item_FessTextView);
                viewHolder.institute_address = (TextView)convertView.findViewById(R.id.item_AddressTextView);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.institute_name.setText(ArrayListPost.get(position).getName());
            viewHolder.institute_address.setText(ArrayListPost.get(position).getAddress());
            viewHolder.institute_price.setText("Fees Rs."+ ArrayListPost.get(position).getFees()+"/-");
            viewHolder.institute_class_type.setText(ArrayListPost.get(position).getClassType());
            viewHolder.institute_timing.setText(ArrayListPost.get(position).getTimings());
            viewHolder.book_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    String Institutes [] = new String[5];
                    Institutes[0]=ArrayListPost.get(position).getName();
                    Institutes[1]=ArrayListPost.get(position).getClassType();
                    Institutes[2]=ArrayListPost.get(position).getAddress();
                    Institutes[3]=ArrayListPost.get(position).getTimings();
                    Institutes[4]=ArrayListPost.get(position).getFees();
                    bundle.putStringArray("Institutes",Institutes);
                    Intent intent = new Intent(MainActivity.this,SamplePage.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            viewHolder.institute_name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    String Institutes [] = new String[5];
                    Institutes[0]=ArrayListPost.get(position).getName();
                    Institutes[1]=ArrayListPost.get(position).getClassType();
                    Institutes[2]=ArrayListPost.get(position).getAddress();
                    Institutes[3]=ArrayListPost.get(position).getTimings();
                    Institutes[4]=ArrayListPost.get(position).getFees();
                    bundle.putStringArray("Institutes",Institutes);
                    Intent intent = new Intent(MainActivity.this,SamplePage.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }
    public static void get_codes(String subcategory[], String locality[], int size_of_subcategory, int size_of_locality)
    {
        subcategory_filter=subcategory;
        locality_filter=locality;
        sub_length=size_of_subcategory;
        local_length=size_of_locality;
      new PostItemsAsyncTask().execute();
    }
    public void status(Boolean result)
    {
        if(result == false){
            Context context = getApplicationContext();
            CharSequence text = "Internet Connection Not Available.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            InstituteAdapter adapter = new InstituteAdapter(getApplicationContext(),R.layout.item_view,postItemArrayList);
            postItemListView.setAdapter(adapter);
        }
    }


    public static class PostItemsAsyncTask extends AsyncTask<String,Void,Boolean>{



         /*   try{
                HttpClient client = new DefaultHttpClient();
                HttpPost post =new HttpPost(params[0]);
                HttpResponse response = client.execute(post);

                int status=response.getStatusLine().getStatusCode();

                if(status==200){
                    HttpEntity entity = response.getEntity();
                    String Data = EntityUtils.toString(entity);*/

//                    JSONObject jsonObject = new JSONObject(Data);
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();

            }

            @Override
            protected Boolean doInBackground(String... arg0) {

                // TODO Auto-generated method stub
                JSONArray jArr1= new JSONArray();
                for(int i=0;i<sub_length;i++){
                    Log.e("jhjr",subcategory_filter[i]+" ");

                    jArr1.put(subcategory_filter[i]);}

                JSONArray jArr2= new JSONArray();
                for(int i=0;i<local_length;i++){
                    Log.e("dkfklkfslk",locality_filter[i]+"");
                    jArr2.put(locality_filter[i]);}


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                if(sub_length==0&&local_length>0)
                {
                    params.add(new BasicNameValuePair("subcategories",""));
                    params.add(new BasicNameValuePair("locality",jArr2.toString()));


                }
                else
                {
                    if(local_length==0&&sub_length>0)
                    {
                        params.add(new BasicNameValuePair("subcategories",jArr1.toString()));

                        params.add(new BasicNameValuePair("locality",""));

                    }
                    else
                    {
                        if(local_length>0&&sub_length>0)
                        {
                            params.add(new BasicNameValuePair("subcategories",jArr1.toString()));

                            params.add(new BasicNameValuePair("locality",jArr2.toString()));
                        }
                        else
                        {
                          if(local_length==0&&sub_length==0)
                          {
                              params.add(new BasicNameValuePair("subcategories",""));

                              params.add(new BasicNameValuePair("locality",""));
                          }
                        }
                    }
                }


                Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");

                JSONObject json = jparser.makeHttpRequest(url_for_institute, "POST", params);
                if (json == null) {
                    message = "No internet connection... please try later";
                    Log.v("tushita", "The Json Object was Null");
                    return null;
                }

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    int k=0;

                    JSONArray jsonArray = json.getJSONArray("institutes");

                    for(int i=0;i<jsonArray.length();i++){
                        PostItems postItems = new PostItems();
                        JSONObject jsonPostItem=jsonArray.getJSONObject(i);
                        postItems.setClassType(jsonPostItem.getString("batch_category"));
                        postItems.setName(jsonPostItem.getString("institute"));
                        postItems.setFees(jsonPostItem.getString("batch_single_price"));



                        postItems.setAddress("Hi Tech City");
                        postItems.setTimings("4am-9pm");
                        postItems.setBookNow("BookNow");
                        postItemArrayList.add(postItems);
                    }
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

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
            case R.id.Logout:
               LoginActivity.logout();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}