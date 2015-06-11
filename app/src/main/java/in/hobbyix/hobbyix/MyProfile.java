package in.hobbyix.hobbyix;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyProfile extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
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
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
