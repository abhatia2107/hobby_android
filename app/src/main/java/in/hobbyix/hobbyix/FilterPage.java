package in.hobbyix.hobbyix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class FilterPage extends ActionBarActivity {
    MyListAdapter adapter;
    MyListAdapter2 adapter2;
    String load_locality_detail[] = new String[100];
    String load_subcategory_detail[] = new String[100];
   //String load_locality_details[][] = new String[500][3];
   // String load_subcategory_details[][] = new String[500][3];
    ArrayList<CheckBoxString> checkBoxList = new ArrayList<CheckBoxString>();
    ArrayList<CheckBoxString> checkBoxList2 = new ArrayList<CheckBoxString>();

    Button reset, filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        reset = (Button) findViewById(R.id.ResetButton);
        filter = (Button) findViewById(R.id.FilterButton);
        load_locality_detail = new Location_batches().store_details_of_locality();
        load_subcategory_detail = new Subcategory_Fitness().store_details_of_subcategory();
        populatecheckBoxList();

        checkButtonClick();

    }

    public void onclickreset(View view) {

    }


    public void populatecheckBoxList() {


        for (int i = 0; i < Integer.valueOf(load_subcategory_detail[0]); i++) {
            checkBoxList.add(new CheckBoxString(load_subcategory_detail[i + 1], false));
        }
        for (int i = 0; i < Integer.valueOf(load_locality_detail[0]); i++) {
            checkBoxList2.add(new CheckBoxString(load_locality_detail[i + 1], false));
        }
        adapter = new MyListAdapter(this,
                R.layout.checkbox_element, checkBoxList);
        ListView list = (ListView) findViewById(R.id.SubCategoriesListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                // When clicked, show a toast with the TextView text
                CheckBoxString checkbox1 = (CheckBoxString) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + checkbox1.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter2 = new MyListAdapter2(this,
                R.layout.checkbox_element, (ArrayList<CheckBoxString>) checkBoxList2);
        ListView list2 = (ListView) findViewById(R.id.LocalityListView);
        list2.setAdapter(adapter2);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                CheckBoxString checkbox2 = (CheckBoxString) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + checkbox2.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }


    private class MyListAdapter extends ArrayAdapter<CheckBoxString> {
        private final ArrayList<CheckBoxString> checkboxlist;
        boolean[] checkBoxState;


        public MyListAdapter(Context context, int textViewResourceId,
                             ArrayList<CheckBoxString> checkboxlist) {
            super(context, textViewResourceId, checkboxlist);
            checkBoxState = new boolean[200];
            this.checkboxlist = new ArrayList<CheckBoxString>();
            this.checkboxlist.addAll(checkboxlist);

        }

        private class ViewHolder {

            CheckBox name;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));
            //CheckBoxString checkBoxString = new CheckBoxString();
            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


                convertView = vi.inflate(R.layout.checkbox_element, null);


                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.ItemCheckBox);


                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        CheckBoxString checkBoxxx = (CheckBoxString) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        checkBoxxx.setSelected(cb.isChecked());
                    }
                });


            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CheckBoxString checkBoxString1 = checkboxlist.get(position);


            holder.name.setText(checkBoxString1.getName());
            holder.name.setChecked(checkBoxString1.isSelected());
            holder.name.setTag(checkBoxString1);
            return convertView;

        }

    }


    private class MyListAdapter2 extends ArrayAdapter<CheckBoxString> {

        private final ArrayList<CheckBoxString> checkboxlist;
        boolean[] checkBoxState;


        public MyListAdapter2(Context context, int textViewResourceId,
                              ArrayList<CheckBoxString> checkboxlist) {
            super(context, textViewResourceId, checkboxlist);
            checkBoxState = new boolean[200];
            this.checkboxlist = new ArrayList<CheckBoxString>();
            this.checkboxlist.addAll(checkboxlist);

        }

        private class ViewHolder {
            CheckBox name;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            ViewHolder holder = null;
            //CheckBoxString checkBoxString = new CheckBoxString();
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.checkbox_element, null);


                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.ItemCheckBox);

                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        CheckBoxString checkBoxxx = (CheckBoxString) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        checkBoxxx.setSelected(cb.isChecked());
                    }
                });


            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CheckBoxString checkBoxString1 = checkboxlist.get(position);

            holder.name.setText(checkBoxString1.getName());
            holder.name.setChecked(checkBoxString1.isSelected());
            holder.name.setTag(checkBoxString1);
            return convertView;

        }

    }

    public void checkButtonClick() {

        Button filter = (Button) findViewById(R.id.FilterButton);
        Button reset=(Button) findViewById(R.id.ResetButton);

        filter.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final String subcategory[]= new String[100];
                final String locality[]=new String[100];

                StringBuffer responseText = new StringBuffer();
                responseText.append("Selected Countries are...\n");

                ArrayList<CheckBoxString> checkboxlists = adapter.checkboxlist;

                for (int i = 0; i < checkboxlists.size(); i++) {
                    CheckBoxString check = checkboxlists.get(i);

                    if (check.isSelected()) {
                        responseText.append("\n" + check.getName());
                    }
                }

                Toast.makeText(getApplicationContext(), responseText,
                        Toast.LENGTH_LONG).show();


                ArrayList<CheckBoxString> checkboxlists2 = adapter2.checkboxlist;

                for (int i = 0; i < checkboxlists2.size(); i++) {
                    CheckBoxString check = checkboxlists2.get(i);

                    if (check.isSelected()) {
                        responseText.append("\n" + check.getName());
                    }
                }
<<<<<<< HEAD
                Filter_page_backend.get_codes(subcategory,locality,l,k);
=======
>>>>>>> e505c5ff76a83514ecedc0f2986a1d5936e9a959

                Toast.makeText(getApplicationContext(), responseText,
                        Toast.LENGTH_LONG).show();
                Intent OpenLogin = new Intent(v.getContext(), Filter_page_backend.class);
                OpenLogin.putExtra("subcategory",subcategory);
                OpenLogin.putExtra("locality",locality);
                OpenLogin.putExtra("length_subcategory",subcategory);
                OpenLogin.putExtra("length_localities",subcategory);

                startActivityForResult(OpenLogin, 0);

            }


        });
        reset.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Selected Countries are...\n");

                ArrayList<CheckBoxString> checkboxlists = adapter.checkboxlist;

                for (int i = 0; i < checkboxlists.size(); i++) {
                    CheckBoxString check = checkboxlists.get(i);

                    if (check.isSelected()) {
                        check.setSelected(false);
                    }
                }

                Toast.makeText(getApplicationContext(), responseText,
                        Toast.LENGTH_LONG).show();


                ArrayList<CheckBoxString> checkboxlists2 = adapter2.checkboxlist;

                for (int i = 0; i < checkboxlists2.size(); i++) {
                    CheckBoxString check = checkboxlists2.get(i);

                    if (check.isSelected()) {
                        check.setSelected(false);
                    }
                }


                Toast.makeText(getApplicationContext(), responseText,
                        Toast.LENGTH_LONG).show();
                Intent OpenLogin = new Intent(v.getContext(), MainActivity.class);

                startActivityForResult(OpenLogin, 0);
            }


        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


