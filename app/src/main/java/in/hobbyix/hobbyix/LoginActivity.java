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
    EditText email_id,password;
    int firstemail=0,firstpassword=0;

    private ProgressDialog pDialog;
    //the progessdialog for progress bar


    //url for checking

    public  String url_login="http://192.168.137.1/Hobbyix/logincheck.php";

    public String details_of_user[] =new String[100];
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
        email_id = (EditText) findViewById(R.id.EmailEditEditView);

        password = (EditText) findViewById(R.id.PasswordEditText);
      /*  LoginButton = (Button) findViewById(R.id.LoginButton);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);*/
        forgetpassword = (TextView) findViewById(R.id.ForgotPassword);
        onclick();
    }
    public void onclick() {

        Button register = (Button) findViewById(R.id.RegisterButton);
        Button login = (Button) findViewById(R.id.LoginButton);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenRegister = new Intent(v.getContext(), RegisterActivity.class);
                startActivityForResult(OpenRegister, 0);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m;
                emailtext = email_id.getText().toString();
                passwordtext = password.getText().toString();

                Log.e("dfksdkf",""+emailtext+" "+passwordtext);
                if (emailtext.equals("") == true || emailtext.equals("user@domain.com") == true) {
                    Toast.makeText(getApplicationContext(), "please fill email", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwordtext.equals("") == true || passwordtext.equals("password") == true) {
                        Toast.makeText(getApplicationContext(), "please fill password", Toast.LENGTH_SHORT).show();
                    } else {

                        details_of_user = set_user();

                        MyProfile.user_details = details_of_user;




                /*else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong Username/Password",Toast.LENGTH_SHORT).show();

                            email.setText("");
                            password.setText("");

                        }*/
                    }
                }
            }
        } );
    }
    public void onclickforgetpassword(View view)
    {
        String emailtext,passwordtext;
        emailtext=email_id.getText().toString();
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
   public String [] set_user()
    {
        String[] aResultM =null;
        try {
            String params = null;
            Login_Details task = new Login_Details();
            task.execute(params);
            aResultM = task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResultM;
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


    class Login_Details extends AsyncTask<String,String,String[]> {

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
        protected String [] doInBackground(String... arg0) {

            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.e("diksdhdgshf","djhfkshgk");
            params.add(new BasicNameValuePair("Email",emailtext));
            params.add(new BasicNameValuePair("Pass",passwordtext));

             JSONArray user_details;
            JSONObject json = jparser.makeHttpRequest(url_login,"POST",params);
            String user_detail[]= new String[100];
            if(json==null){
                message = "No internet connection... please try later";

                return null;
            }

            try {
                if (json.getInt("success") == 1) {
                    guidelines = json.getJSONArray("user");

                    JSONObject c = guidelines.getJSONObject(0);
                    user_detail[0] = c.getString("fname");
                    user_detail[1] = c.getString("lname");
                    user_detail[2] = c.getString("city");
                    user_detail[3] = c.getString("mobileno");
                    user_detail[4] = c.getString("email");


                }
            }

                catch (JSONException e1) {
                e1.printStackTrace();
            }



            return user_detail;
        }

        @Override
        protected void onPostExecute(String result[]) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            pDialog.dismiss();
        }
    }
}
