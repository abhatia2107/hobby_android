package in.hobbyix.hobbyix;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class SamplePage extends ActionBarActivity {
    TextView InstituteName;
    TextView InstituteClassType;
    TextView InstituteAddress;
    Button proceedButton;
    Spinner spinner; String SessionNumber;
    ArrayAdapter<CharSequence> adpter;
    private TextView Output;
    private int year;
    private int month;
    private int day;
    static final int DATE_PICKER_ID = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_classes);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        spinner=(Spinner)findViewById(R.id.NumberOfSessionSpinner);
        adpter=ArrayAdapter.createFromResource(this,R.array.list_numbers,android.R.layout.simple_list_item_1);
        adpter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adpter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SessionNumber = parent.getItemAtPosition(position) + "";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Output = (TextView) findViewById(R.id.editText4);
        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        Output.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });
        proceedButton = (Button)findViewById(R.id.ProceedButton);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PaymentIntent = new Intent(v.getContext(), PaymentPage.class);
                startActivity(PaymentIntent);
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String InstituteDetails[] = new String[5];
            InstituteDetails = extras.getStringArray("Institutes");
            InstituteName = (TextView)findViewById(R.id.HeaderInstituteName);
            InstituteName.setText(InstituteDetails[0]);
            InstituteClassType=(TextView)findViewById(R.id.HeaderInstituteClassType);
            InstituteClassType.setText(InstituteDetails[1]);
            InstituteAddress = (TextView)findViewById(R.id.HeaderInstituteAddress);
            InstituteAddress.setText(InstituteDetails[2]);
        }
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
<<<<<<< HEAD
            case DATE_PICKER_ID:
=======
                case DATE_PICKER_ID:
                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
<<<<<<< HEAD
>>>>>>> parent of 83c57a3... VISIBILITY = VISIBLE AND VISIBILITY = GONE MADE SAMPLE PAGE MODIFIED
=======
>>>>>>> parent of 83c57a3... VISIBILITY = VISIBLE AND VISIBILITY = GONE MADE SAMPLE PAGE MODIFIED
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
<<<<<<< HEAD
        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
=======
        private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
         // when dialog box is closed, below method will be called.
<<<<<<< HEAD
>>>>>>> parent of 83c57a3... VISIBILITY = VISIBLE AND VISIBILITY = GONE MADE SAMPLE PAGE MODIFIED
=======
>>>>>>> parent of 83c57a3... VISIBILITY = VISIBLE AND VISIBILITY = GONE MADE SAMPLE PAGE MODIFIED
        @Override
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            Output.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fitness_classes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_login:
                Intent LoginIntent = new Intent(this, LoginActivity.class);
                this.startActivity(LoginIntent);
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
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
