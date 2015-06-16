package in.hobbyix.hobbyix;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RegisterActivity extends ActionBarActivity {
    EditText email,password,phoneno,fullname;
    int firstemail=0,firstpassword=0,firstphoneno=0,firstfullname=0;
    String emailtext,passwordtext,phonenotext,fullnametext;

    //the progessdialog for progress bar
    private ProgressDialog pDialog ;
    //url to get required guidelines

    private static String url_of_adding_user = "http://192.168.137.1/Hobbyix/adding_user.php";


    String message = null;
    //object for JSONParser class
    JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values
    ArrayList<HashMap<String,String>> guidelist;
    //JSONArray(inbuilt) to extract the JSONArray
    JSONArray guidelines = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        email=(EditText)findViewById(R.id.EmailEditView);
        password=(EditText)findViewById(R.id.PasswordEditText);
        phoneno=(EditText)findViewById(R.id.PhonenoEditText);
        fullname=(EditText)findViewById(R.id.FullnameEditText);

    }

    public void onclickregister(View v) throws UnsupportedEncodingException, JSONException {

        emailtext = email.getText().toString();
        passwordtext = password.getText().toString();
        fullnametext = fullname.getText().toString();
        phonenotext = phoneno.getText().toString();

        if (emailtext.equals("") == true || emailtext.equals("user@domain.com") == true) {
            Toast.makeText(getApplicationContext(), "please add your email", Toast.LENGTH_SHORT).show();
        } else {
            if (passwordtext.equals("") == true || passwordtext.equals("password") == true) {
                Toast.makeText(getApplicationContext(), "please add your password", Toast.LENGTH_SHORT).show();
            } else {

                if (fullnametext.equals("") == true || fullnametext.equals("John Smith") == true)
                    Toast.makeText(getApplicationContext(), "please add your fullname", Toast.LENGTH_SHORT).show();
                else if (phonenotext.equals("") == true || phonenotext.equals("+91-1234567890") == true)
                    Toast.makeText(getApplicationContext(), "please add your phoneno", Toast.LENGTH_SHORT).show();
                else {
                    int p = 0;

                    if (phonenotext.length() < 10 || phonenotext.length()> 10)
                        Toast.makeText(getApplicationContext(), "Length of phoneno  should be 10", Toast.LENGTH_SHORT).show();

                    char c;
                    int k = 0;
                    while (k != 10) {
                        c = phonenotext.charAt(k);

                        if (c ==' ') {

                            p = 1;
                            Toast.makeText(getApplicationContext(), "Phone no should not contain space", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        k++;
                    }

                    if (p == 0) {
                        Log.e("oioio", "popop");
                        new LoadAllGuidelines().execute();
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
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class LoadAllGuidelines extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            Log.e("opopo","oiioi");
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("please wait....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected String doInBackground(String... arg0) {

            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.e("diksdhdgshf","djhfkshgk");
            params.add(new BasicNameValuePair("Email",emailtext));
            params.add(new BasicNameValuePair("Pass",passwordtext));
            params.add(new BasicNameValuePair("Name",fullnametext));
            params.add(new BasicNameValuePair("Phoneno",phonenotext));

            JSONObject json = jparser.makeHttpRequest(url_of_adding_user,"POST",params);

            if(json==null){
                message = "No internet connection... please try later";
                Log.e("garvit","gf");
                return null;
            }

            try {
                if(json.getInt("success")==1)
                {
                    /*guidelines = json.getJSONArray(TAG_CALAMITIES);
                    for(int i=0;i<guidelines.length();i++)
                    {
                        JSONObject c = guidelines.getJSONObject(i);

                        Integer id = c.getInt(TAG_ID);
                        String calamity = c.getString(TAG_CALAMITY);
                        String before_cal = c.getString(TAG_BEFORE_CAL);
                        String after_cal = c.getString(TAG_AFTER_CAL);
                        String during_cal = c.getString(TAG_DURING_CAL);

                        HashMap<String,String> map = new HashMap<String,String>();
                        map.put(TAG_CALAMITY, calamity);
                        map.put(TAG_ID, id.toString());
                        map.put(TAG_BEFORE_CAL, before_cal);
                        map.put(TAG_DURING_CAL, during_cal);
                        map.put(TAG_AFTER_CAL, after_cal);

                        guidelist.add(map);
                    }*/

                    //  Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Log.e("Registered success","sdasd");
                    Intent OpenLogin = new Intent(getApplicationContext(), MainActivity.class);

                    startActivityForResult(OpenLogin, 0);

                }else{

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            pDialog.dismiss();
        }

    }
}
