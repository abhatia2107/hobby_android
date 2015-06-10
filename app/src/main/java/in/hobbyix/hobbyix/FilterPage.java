package in.hobbyix.hobbyix;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class FilterPage extends ActionBarActivity {
    private List<CheckBoxString> checkBoxList = new ArrayList<CheckBoxString>();
    private List<CheckBoxString> checkBoxList2 = new ArrayList<CheckBoxString>();
    String load_locality_details[]=new String[25];
    String load_subcategory_details[]=new String[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        load_locality_details = new Location_batches().store_details_of_locality();
        load_subcategory_details=new Subcategory_Fitness().store_details_of_subcategory();
        populatecheckBoxList();
        populateListView();

    }
    private void populatecheckBoxList(){
        for(int i=0;i<10;i++) {
            checkBoxList2.add(new CheckBoxString(load_locality_details[i]));
        }
        for(int i=0;i<20;i++) {
            checkBoxList.add(new CheckBoxString(load_subcategory_details[i]));
        }
    }
    private void populateListView() {
        ArrayAdapter<CheckBoxString> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.SubCategoriesListView);
        list.setAdapter(adapter);
        ArrayAdapter<CheckBoxString> adapter2 = new MyListAdapter2();
        ListView list2 = (ListView) findViewById(R.id.LocalityListView);
        list2.setAdapter(adapter2);
    }
    private class MyListAdapter extends ArrayAdapter<CheckBoxString> {
        public MyListAdapter() {
            super(FilterPage.this, R.layout.checkbox_element, checkBoxList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.checkbox_element, parent, false);
            }
            CheckBoxString CurrentString=checkBoxList.get(position);
            CheckBox checkbox = (CheckBox)itemView.findViewById(R.id.ItemCheckBox);
            checkbox.setText(CurrentString.getMainString());
            return itemView;
        }
    }
    private class MyListAdapter2 extends ArrayAdapter<CheckBoxString> {
        public MyListAdapter2() {
            super(FilterPage.this, R.layout.checkbox_element, checkBoxList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.checkbox_element, parent, false);
            }
            CheckBoxString CurrentString=checkBoxList2.get(position);
            CheckBox checkbox = (CheckBox)itemView.findViewById(R.id.ItemCheckBox);
            checkbox.setText(CurrentString.getMainString());
            return itemView;
        }
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
