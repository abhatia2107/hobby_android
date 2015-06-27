package in.hobbyix.hobbyix;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SplashScreen extends Activity {
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                   /* isInternetPresent = cd.isConnectingToInternet();

                    // check for Internet status
                    if (isInternetPresent) {*/
                        // Internet Connection is Present
                        // make HTTP requests
                        Intent openTermsCondition = new Intent(getApplicationContext(),Institute_Details.class);
                        startActivity(openTermsCondition);
                        finish();

                    /*} else {
                        Log.e("sfjfk", "  fgjkdfg");
                        // Internet connection is not present
                        // Ask user to connect to Internet
                        showAlertDialog(SplashScreen.this, "No Internet Connection",
                                "You don't have internet connection.");
                       // Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }*/
                }


                }

        };
        timer.start();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
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
    public void showAlertDialog(Context context, String title, String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

alertDialog.setIcon(R.drawable.button_cancel);       // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}