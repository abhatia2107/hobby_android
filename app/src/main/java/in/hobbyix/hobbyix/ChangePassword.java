package in.hobbyix.hobbyix;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChangePassword extends ActionBarActivity {
EditText currentpassword,newpassword,email;
    String currpass,newpass,emailtext;
    Button submit;
    //url to get required guidelines
    private static String url_for_change_password = "http://192.168.10.104/Hobbyix/change_password.php";


    String message = null;
    //object for JSONParser class
    JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values
    ArrayList<HashMap<String,String>> localitylist;
    //JSONArray(inbuilt) to extract the JSONArray
    JSONArray localitylines = null;
    TextView institute_name;
    //-----------------------------------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        currentpassword=(EditText)findViewById(R.id.current_password);
        newpassword=(EditText)findViewById(R.id.new_password);
        email=(EditText)findViewById(R.id.email);
        submit=(Button)findViewById(R.id.submit);

    }

    public int status_of_change_password() {

        int aResult=0;
        try {
            String params = null;
            PostChangePassword task = new  PostChangePassword();
            task.execute(params);
            aResult= task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResult;

    }
    public void submit_click(View v)
    {
        int p=0;
      currpass=currentpassword.getText().toString();
        newpass=newpassword.getText().toString();
        emailtext=email.getText().toString();
        if (emailtext.equals("") == true || emailtext.equals("user@domain.com") == true) {
            Toast.makeText(getApplicationContext(), "please add your email", Toast.LENGTH_SHORT).show();
        } else {
            if (currpass.equals("") == true || currpass.equals("password") == true)
                Toast.makeText(getApplicationContext(), "please add your current pasword", Toast.LENGTH_SHORT).show();

            else {
                if (newpass.equals("") == true || newpass.equals("password") == true)
                    Toast.makeText(getApplicationContext(), "please add your new  password", Toast.LENGTH_SHORT).show();

                else {
                    if (newpass.equals(currpass) == true)
                        Toast.makeText(getApplicationContext(), "new password should not be equal to current password", Toast.LENGTH_SHORT).show();
                    else {
                        p = 1;
                    }
                       if (p == 1) {
                            Log.e("oioio", "popop");
                           int m= status_of_change_password();
                           if(m==0)
                               Toast.makeText(getApplicationContext(), "Wrong email or current password", Toast.LENGTH_SHORT).show();
                           else
                           {
                               if(m==1)
                           {
                               Toast.makeText(getApplicationContext(), "Congrats!! your password has been changed", Toast.LENGTH_SHORT).show();
                               Intent OpenLogin = new Intent(v.getContext(), LoginActivity.class);

                               startActivityForResult(OpenLogin, 0);
                           }
                           }

                        }

                   /* if(k==1){
                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent OpenLogin = new Intent(v.getContext(), Welcome.class);

                    startActivityForResult(OpenLogin, 0);}*/
                    }
                }
            }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
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

 class PostChangePassword extends AsyncTask<String, String, Integer> {


     @Override
     protected void onPreExecute() {
         // TODO Auto-generated method stub
         super.onPreExecute();
       /* pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading all Products Please Wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();*/

     }

     @Override
     protected Integer doInBackground(String... arg0) {

         // TODO Auto-generated method stub
         String locality_details_list[] = new String[0];
         List<NameValuePair> params = new ArrayList<NameValuePair>();

         params.add(new BasicNameValuePair("email", emailtext));

         params.add(new BasicNameValuePair("currentpassword", currpass));
         params.add(new BasicNameValuePair("newpassword", newpass));

         JSONObject json = jparser.makeHttpRequest(url_for_change_password, "POST", params);
         if (json == null) {
             message = "No internet connection... please try later";
             Log.v("tushita", "The Json Object was Null");
             return -1;
         }
         try {
             int success = json.getInt("success");
             if (success == 1) {
                 Log.e("jkjhkjgdh", "success");
                 return 1;

                   /* localitylines =  json.getJSONArray(TAG_LOCALITY );
                    locality_details_list=new String[localitylines.length()+1];
                    Log.e("jjkfdsffffkkk",localitylines.length()+"");
                    int j;
                    locality_details_list[0]=Integer.toString(localitylines.length());
                    for(int i=0;i<localitylines.length();i++)
                    {
                        j=i+1;
                        JSONObject c=  localitylines.getJSONObject(i);

                        // Integer id = c.getInt(TAG_ID);
                        String name_of_locality = c.getString("locality");

                        Log.e("dhkajhdj",""+name_of_locality+"");

                        locality_details_list[j]=name_of_locality;

                    }*/
                 //  Intent in = new Intent(getApplicationContext(),SQLtry.class);
                 //in.putExtra("guidelist",guidelist);
                 //startActivity(in);

             } else {
                 return 0;
                 //Intent in = new Intent(getApplicationContext(),SQLtry.class);
                 //in.putExtra("guidelist",guidelist);
                 //finish();
                 //startActivity(in);
             }

         } catch (JSONException e) {
             e.printStackTrace();
         }

   return 0;
     }


    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        // pDialog.dismiss();
    }
}
}
