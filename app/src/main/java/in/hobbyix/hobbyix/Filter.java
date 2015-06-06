package in.hobbyix.hobbyix;

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


public class Filter extends ActionBarActivity {
    private List<CheckBoxString> checkBoxList = new ArrayList<CheckBoxString>();
    private List<CheckBoxString> checkBoxList2 = new ArrayList<CheckBoxString>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        populatecheckBoxList();
        populateListView();

    }
    private void populatecheckBoxList(){
        for(int i=1;i<=10;i++){
            checkBoxList.add(new CheckBoxString("Dummy1Data"+i));
            checkBoxList2.add(new CheckBoxString("Dummy2data"+i));
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
            super(Filter.this, R.layout.checkbox_element, checkBoxList);
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
            super(Filter.this, R.layout.checkbox_element, checkBoxList);
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
}
