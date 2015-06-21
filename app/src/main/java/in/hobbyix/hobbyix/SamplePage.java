package in.hobbyix.hobbyix;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
<<<<<<< HEAD
import android.widget.EditText;
=======
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
>>>>>>> 83c57a36168a6c3a8b46b8ee24d5645aa4e7428a
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;

public class SamplePage extends ActionBarActivity {
    TextView InstituteName;
    TextView InstituteClassType;
    TextView InstituteAddress;
    Spinner spinner; String SessionNumber;
    ArrayAdapter<CharSequence> adpter;
    private TextView Output,total_amount;
    private int year;
    private int month;
    private int day;
    TextView payment;
    String booking_date;
    EditText promo;
    String no_of_sessions;
    static final int DATE_PICKER_ID = 1111;
    String details[]= new String[100];
    TextView basic_price,Institute_name,Institute_categories,Institute_Address,Institute__Address,Institute_Mobileno,Institute_timings,Institute_Location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        payment=(TextView)findViewById(R.id.textView10);
        Institute_Address=(TextView)findViewById(R.id.InstituteAddressHeader);
        Institute_categories=(TextView)findViewById(R.id.InstituteClassType);
        Institute_name=(TextView)findViewById(R.id.InstituteName);
        Institute__Address=(TextView)findViewById(R.id.InstituteAddress);
        Institute_Location=(TextView)findViewById(R.id.LocationTextView);
        Institute_Mobileno=(TextView)findViewById(R.id.InstituteMobileNumberText);
        Institute_timings=(TextView)findViewById(R.id.textView);

<<<<<<< HEAD
        basic_price=(TextView)findViewById(R.id.textView10);
        spinner=(Spinner)findViewById(R.id.spinner);
        promo=(EditText)findViewById(R.id.editText);
        total_amount=(TextView)findViewById(R.id.textView11);
=======
        spinner=(Spinner)findViewById(R.id.NumberOfSessionSpinner);
>>>>>>> 83c57a36168a6c3a8b46b8ee24d5645aa4e7428a
        adpter=ArrayAdapter.createFromResource(this,R.array.list_numbers,android.R.layout.simple_list_item_1);
        adpter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adpter);
      details= Smple_page_backend.store_details();
        for(int i=0;i<14;i++)
            Log.e("op", "" + details[i]);

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
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        Output.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);

            }
        });
<<<<<<< HEAD

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String promo_code=promo.getText().toString();
                String after_applying_promo_code = null;

                //total_amount.setText(after_applying_promo_code);

            }
            });


    /*   public String [] send_order_details()
        {
            String [] details=new String[5];
            details[0]=;
            details[1]=no_of_sessions;
            details[2]=booking_date;
            details[3]=;


        }*/

        proceedButton = (Button)findViewById(R.id.ProceedButton);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking_date=Output.getText().toString();
                no_of_sessions = spinner.getSelectedItem().toString();
                Intent PaymentIntent = new Intent(v.getContext(), PaymentPage.class);
                startActivity(PaymentIntent);
=======
        Button proceedButton = (Button)findViewById(R.id.ProceedButton);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout BookingView = (RelativeLayout)findViewById(R.id.BookingView);
                BookingView.setVisibility(View.GONE);
                LinearLayout PaymentView  = (LinearLayout)findViewById(R.id.PaymentView);
                PaymentView.setVisibility(View.VISIBLE);
            }
        });
        Button BackToPayment = (Button)findViewById(R.id.BackToPayment);
        BackToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout BookingView = (RelativeLayout)findViewById(R.id.BookingView);
                BookingView.setVisibility(View.VISIBLE);
                LinearLayout PaymentView  = (LinearLayout)findViewById(R.id.PaymentView);
                PaymentView.setVisibility(View.GONE);
>>>>>>> 83c57a36168a6c3a8b46b8ee24d5645aa4e7428a
            }
        });
     /*   Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String InstituteDetails[] = new String[5];
            InstituteDetails = extras.getStringArray("Institutes");
            InstituteName = (TextView)findViewById(R.id.HeaderInstituteName);
            InstituteName.setText(InstituteDetails[0]);
            InstituteClassType=(TextView)findViewById(R.id.HeaderInstituteClassType);
            InstituteClassType.setText(InstituteDetails[1]);
            InstituteAddress = (TextView)findViewById(R.id.HeaderInstituteAddress);
            InstituteAddress.setText(InstituteDetails[2]);
        }*/
        set_everything(details);
    }
    public void set_everything(String detail[])
    {
        Institute_name.setText(detail[0]);
        Institute_categories.setText(detail[1]);
        Institute_Address.setText(detail[2]);
        Institute__Address.setText(detail[2]);
        basic_price.setText("Rs."+detail[3]);
        Institute_Location.setText(detail[4]);
        Institute_Mobileno.setText(detail[13]);
        Institute_timings.setText(detail[12]);


    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
                case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
            }
        return null;
        }
        private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
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
