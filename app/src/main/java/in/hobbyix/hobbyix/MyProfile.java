package in.hobbyix.hobbyix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MyProfile extends ActionBarActivity {
    static TextView email;
    static TextView name;
    static TextView mobileno;
    static TextView city;
    static String[] user_details = new String[100];
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        email=(TextView)findViewById(R.id.email_id);
        name=(TextView)findViewById(R.id.name);
        mobileno=(TextView)findViewById(R.id.mobile_number);
        city=(TextView)findViewById(R.id.city);
        settings = getSharedPreferences(PREFS_NAME, 0);
        boolean hasLoggedIn =settings.getBoolean("hasLoggedIn", false);
        if(hasLoggedIn==true)
           set_profile_contents();
    }
    public static void set_profile_contents()
    {
        String name_person=null;
        if(user_details!=null) {
            name_person = user_details[0].concat(" ");
            name_person = name_person.concat(user_details[1]);

            name.setText("NAME: " + name_person);
            email.setText("E-MAIL: " + user_details[4]);
            city.setText("CITY: " + user_details[2]);
            mobileno.setText("MOBILE NUMBER: " + user_details[3]);
        }
        else

        {

            name.setText("NAME: " );
            email.setText("E-MAIL: " );
            city.setText("CITY: " );
            mobileno.setText("MOBILE NUMBER: " );

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
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
            case R.id.Logout:
                LoginActivity.logout();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
