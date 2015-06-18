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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ForgotPassword extends ActionBarActivity {
    EditText email;
    Button submit;
    String emailtext;
    private static String url_of_forgot_password = "http://192.168.137.1/Hobbyix/forgot_password.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email=(EditText)findViewById(R.id.ForgotEmail);
        submit=(Button)findViewById(R.id.SubmitButton);


    }
    public void onclicksubmit(View view)
    {
        emailtext = email.getText().toString();
        if (emailtext.equals("") == true || emailtext.equals("Enter Your E-mail") == true) {
            Toast.makeText(getApplicationContext(), "please add your email", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String x=set_user();
            if(x=="1")
            {

            }
            else
            {

            }

        }
    }
    public String set_user()
    {
        String aResultM = null;
        try {
            String params = null;
            Forgot_Password_asyn  task = new Forgot_Password_asyn ();
            task.execute(params);
            aResultM = task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResultM;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
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
    class Forgot_Password_asyn extends AsyncTask<String,String,String> {


        private JSONParser jparser;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();


        }
        @Override
        protected String doInBackground(String... arg0) {

            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.e("diksdhdgshf","djhfkshgk");
            params.add(new BasicNameValuePair("Email",emailtext));

             String success =null;
            JSONObject json = jparser.makeHttpRequest(url_of_forgot_password,"POST",params);

            if(json==null){


                return null;
            }

            try {
                if(json.getInt("success")==1)
                {
                    success=Integer.toString(json.getInt("success"));
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


                }else{

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return success;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

        }

    }
}
