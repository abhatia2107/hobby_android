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
import java.util.HashMap;
import java.util.Map;


public class FilterPage extends ActionBarActivity {
    MyListAdapter adapter;
    MyListAdapter2 adapter2;
    ArrayList<HashMap<String, String>>  load_locality_detail = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>>  load_subcategory_detail = new ArrayList<HashMap<String, String>>();
    //String load_locality_details[][] = new String[500][3];
   // String load_subcategory_details[][] = new String[500][3];
    ArrayList<CheckBoxString> checkBoxList = new ArrayList<CheckBoxString>();
    ArrayList<CheckBoxString> checkBoxList2 = new ArrayList<CheckBoxString>();
    int length_sub;
    int length_local;

    Button reset, filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ConnectionDetector c= new ConnectionDetector(getApplicationContext());
        Boolean x=c.isConnectingToInternet();
        if(true){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        reset = (Button) findViewById(R.id.ResetButton);
        filter = (Button) findViewById(R.id.FilterButton);
        load_locality_detail =  Location_batches.store_details_of_locality();
        load_subcategory_detail =  Subcategory_Fitness.store_details_of_subcategory();
        length_sub=Subcategory_Fitness.length;
        length_local=Location_batches.length;
        populatecheckBoxList();

        checkButtonClick();}
        else
        {
            Toast.makeText(getApplicationContext(), "Internet Connection not available", Toast.LENGTH_SHORT).show();
        }

    }

    public void onclickreset(View view) {

    }


    public void populatecheckBoxList() {
        String name=null,id=null;

        for(HashMap<String, String> map: load_subcategory_detail) {

            for(Map.Entry<String, String> mapEntry: map.entrySet()) {
                String key = mapEntry.getKey();
                //key=
                String value = mapEntry.getValue();
                if(key=="subcategory")
                    name=value;
                else
                    id=value;


                }

            checkBoxList.add(new CheckBoxString(name,id, false));
        }
        for(HashMap<String, String> map: load_locality_detail) {

            for(Map.Entry<String, String> mapEntry: map.entrySet()) {
                String key = mapEntry.getKey();
                //key=
                String value = mapEntry.getValue();
                if(key=="locality")
                    name=value;
                else
                    id=value;


            }

            checkBoxList2.add(new CheckBoxString(name,id, false));
        }

        adapter = new MyListAdapter(this,
                R.layout.checkbox_element, checkBoxList);
        ListView list = (ListView) findViewById(R.id.SubCategoriesListView);
        list.setAdapter(adapter);

        adapter2 = new MyListAdapter2(this,
                R.layout.checkbox_element, (ArrayList<CheckBoxString>) checkBoxList2);
        ListView list2 = (ListView) findViewById(R.id.LocalityListView);
        list2.setAdapter(adapter2);



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

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        CheckBoxString _state = (CheckBoxString) cb.getTag();



                        _state.setSelected(cb.isChecked());
                    }
                });

                convertView.setTag(holder);



            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CheckBoxString checkBoxString1 = checkboxlist.get(position);


            holder.name.setText(checkBoxString1.getName());
            Log.e("hj",checkBoxString1.getCode());

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
                final String subcategory[] = new String[400];
                final String locality[] = new String[400];
                int l = 0;
                int k = 0,f=0;
                int z = 0;
                StringBuffer responseText = new StringBuffer();
                responseText.append("Selected Countries are...\n");

                ArrayList<CheckBoxString> checkboxlists = adapter.checkboxlist;

                for (int i = 0; i < checkboxlists.size(); i++) {
                    CheckBoxString check = checkboxlists.get(i);

                    if (check.isSelected()) {
                        f=1;
                        subcategory[k++] = check.getCode();

                        Log.e("dhdfhdfhdfhf", "   " + check.getCode());
                        responseText.append("\n" + check.getName());
                    }
                }



                ArrayList<CheckBoxString> checkboxlists2 = adapter2.checkboxlist;

                for (int i = 0; i < checkboxlists2.size(); i++) {
                    CheckBoxString check = checkboxlists2.get(i);

                    if (check.isSelected()) {
                        z = 1;
                        locality[l++] = check.getCode();
                        Log.e("uriir", "  " + check.getCode());
                        responseText.append("\n" + check.getName());
                    }
                }
                Log.e("ioioio","  "+z+"  "+f);

              if (z == 0 && f == 0) {
                    Toast.makeText(getApplicationContext(), "Select at least one", Toast.LENGTH_SHORT).show();


                } else {
                    ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();

                    guidelist = Filter_page_backend.get_codes(subcategory, locality, k, l);
                    for (HashMap<String, String> map : guidelist)

                        for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                            String key = mapEntry.getKey();
                            //key=
                            String value = mapEntry.getValue();
                            Log.e("fjgdf", "   " + key + "     " + value);


                        }
                    String length = Integer.toString(Filter_page_backend.length);

                    Intent OpenLogin = new Intent(v.getContext(), MainActivity.class);
                    OpenLogin.putExtra("guidelist", guidelist);
                    OpenLogin.putExtra("length", length);
                    startActivityForResult(OpenLogin, 0);


             }

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
                        Log.e("SELECTED","Dfk");
                        check.setSelected(false);
                    }
                }




                ArrayList<CheckBoxString> checkboxlists2 = adapter2.checkboxlist;

                for (int i = 0; i < checkboxlists2.size(); i++) {
                    CheckBoxString check = checkboxlists2.get(i);

                    if (check.isSelected()) {
                        check.setSelected(false);
                    }
                }



                Intent OpenLogin = new Intent(v.getContext(), Institute_Details.class);

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


