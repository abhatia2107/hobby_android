package in.hobbyix.hobbyix;

import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarException;

public class MainActivity extends ActionBarActivity {
    private final String instituteUrl="http://192.168.10.108/Hobbyix/displaying_institute_details.php";
    ListView postItemListView;
    InstituteAdapter adapter;
    ArrayList<PostItems> postItemArrayList;
    public static Bundle main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main=savedInstanceState;
        setContentView(R.layout.activity_main);
        postItemListView = (ListView)findViewById(R.id.listView);
        postItemArrayList = new ArrayList<>();
        new PostItemsAsyncTask().execute(instituteUrl);


    }
    public class PostItemsAsyncTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {

            try{
                HttpClient client = new DefaultHttpClient();
                HttpPost post =new HttpPost(params[0]);
                HttpResponse response = client.execute(post);

                int status=response.getStatusLine().getStatusCode();

                if(status==200){
                    HttpEntity entity = response.getEntity();
                    String Data = EntityUtils.toString(entity);

                    JSONObject jsonObject = new JSONObject(Data);

                    JSONArray jsonArray = jsonObject.getJSONArray("institutes");

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
            } catch (ClientProtocolException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
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