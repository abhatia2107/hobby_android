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
import android.widget.TextView;
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

public class LoginActivity extends ActionBarActivity {
    Button LoginButton,RegisterButton;
    String emailtext,passwordtext;
    TextView forgetpassword;
    EditText email,password;
    int firstemail=0,firstpassword=0;

    private ProgressDialog pDialog;
    //the progessdialog for progress bar


    //url for checking
    public  String url_login="http://192.168.10.108/Hobbyix/logincheck.php";

    String message = null;

    //object for JSONParser class
    JSONParser jparser = new JSONParser();


    // ArrayList of HashMaps to store the JSONArray of mapped values
    ArrayList<HashMap<String,String>> guidelist;
    //JSONArray(inbuilt) to extract the JSONArray
    JSONArray guidelines = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.EmailEditView);

        password=(EditText)findViewById(R.id.PasswordEditText);
        LoginButton = (Button)findViewById(R.id.LoginButton);
        RegisterButton=(Button)findViewById(R.id.RegisterButton);
        forgetpassword=(TextView)findViewById(R.id.ForgotPassword);



        RegisterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent OpenRegister = new Intent(v.getContext(),RegisterActivity.class);
                startActivityForResult(OpenRegister,0);
            }
        });
    }
    public void onclicklogin(View v) throws UnsupportedEncodingException {
        int m;

        emailtext=email.getText().toString();
        passwordtext=password.getText().toString();
        if(emailtext.equals("")==true||emailtext.equals("user@domain.com")==true)
        {
            Toast.makeText(getApplicationContext(),"please fill email",Toast.LENGTH_SHORT).show();
        }else {
            if (passwordtext.equals("")==true||passwordtext.equals("password")==true) {
                Toast.makeText(getApplicationContext(), "please fill password", Toast.LENGTH_SHORT).show();
            } else {

              /*  m=0;
              //  m=Posting_on_server.Check(emailtext, passwordtext);
                if(m==1){

                    Log.e("oioio","popop");*/
                new ConnectiontoInternet().execute();

            }
                /*else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Username/Password",Toast.LENGTH_SHORT).show();

                    email.setText("");
                    password.setText("");

                }*/
        }

    }
    public void onclickforgetpassword(View view)
    {
        String emailtext,passwordtext;
        emailtext=email.getText().toString();
        passwordtext=password.getText().toString();
        if(emailtext.equals("")==true||emailtext.equals("user@domain.com")==true)
        {
            Toast.makeText(getApplicationContext(),"please add an email",Toast.LENGTH_SHORT).show();
        }else {
            if (passwordtext.equals("")==true||passwordtext.equals("password")==true) {
                Toast.makeText(getApplicationContext(), "please add a password", Toast.LENGTH_SHORT).show();
            } else {
                Intent i= new Intent(this,ForgotPassword.class);
                startActivity(i);
            }
        }
        // Toast.makeText(getApplicationContext(),"please fill emokklkl",Toast.LENGTH_SHORT).show();

        //  Intent i= new Intent(this,forgetpassword.class);
        // startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


    class ConnectiontoInternet extends AsyncTask<String,String,String> {




        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            Log.e("opopo", "oiioi");
            pDialog = new ProgressDialog(LoginActivity.this);
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


            JSONObject json = jparser.makeHttpRequest(url_login,"GET",params);

            if(json==null){
                message = "No internet connection... please try later";
                // Log.e("garvit","gf");
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

                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
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
