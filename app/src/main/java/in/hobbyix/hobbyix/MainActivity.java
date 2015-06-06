package in.hobbyix.hobbyix;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private List<Post> PostList=new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populatePostList();
        populatePostListView();
        registerClickCallback();
    }
    private void populatePostList() {
        PostList.add(new Post("Endurance Workout Lounge", "Zumba Class", "Hi Tech City", "Mon-Sat: 5am-6pm", "₹ " + "200"+" / Session", R.id.item_book_now_button));
        PostList.add(new Post("Endurance Workout Lounge", "Kick Boxing", "Hi Tech City", "Mon-Sat: 5am-6pm", "₹ " + "200"+" / Session", R.id.item_book_now_button));
        PostList.add(new Post("Endurance Workout Lounge", "Kettle Bell", "Hi Tech City", "Mon-Sat: 5am-6pm", "₹ " + "200"+" / Session", R.id.item_book_now_button));
        PostList.add(new Post("Endurance Workout Lounge", "Gymnastics", "Hi Tech City", "Mon-Sat: 5am-6pm", "₹ "  + "200"+" / Session", R.id.item_book_now_button));
        PostList.add(new Post("Endurance Workout Lounge", "Jijutsu", "Hi Tech City", "Mon-Sat: 5am-6pm", "₹ " +"200"+" / Session", R.id.item_book_now_button));
        PostList.add(new Post("Endurance Workout Lounge", "Aerobics", "Hi Tech City", "Mon-Sat: 5am-6pm", "₹ " +"200"+" / Session", R.id.item_book_now_button));
        PostList.add(new Post("Endurance Workout Lounge", "Karate Class", "Hi Tech City", "Mon-Sat: 5am-6pm","₹ " + "200"+" / Session", R.id.item_book_now_button));
    }
    private void populatePostListView() {
        ArrayAdapter<Post> adapter=new MyListAdapter();
        ListView postlist=(ListView)findViewById(R.id.listView);
        postlist.setAdapter(adapter);
    }
    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Intent appInfo = new Intent(viewClicked.getContext(), FitnessClasses.class);
                startActivity(appInfo);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Post>{
        public MyListAdapter(){
            super(MainActivity.this,R.layout.item_view,PostList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }
            Post CurrentPost = PostList.get(position);
            TextView InstituteName=(TextView)itemView.findViewById(R.id.item_NameTextView);
            InstituteName.setText(CurrentPost.getName());
            InstituteName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Payment = new Intent(v.getContext(),FitnessClasses.class);
                    startActivityForResult(Payment,0);
                }
            });

            TextView ClassType=(TextView)itemView.findViewById(R.id.item_ClassTypeTextView);
            ClassType.setText(CurrentPost.getClassType());

            TextView InstituteAddress=(TextView)itemView.findViewById(R.id.item_AddressTextView);
            InstituteAddress.setText(CurrentPost.getAddress());

            TextView InstituteTimings=(TextView)itemView.findViewById(R.id.item_TimingsTextView);
            InstituteTimings.setText(CurrentPost.getTimings());

            TextView InstituteFees=(TextView)itemView.findViewById(R.id.item_FessTextView);
            InstituteFees.setText(CurrentPost.getFees());

            Button BookNow=(Button)itemView.findViewById(R.id.item_book_now_button);
            BookNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Payment = new Intent(v.getContext(),FitnessClasses.class);
                    startActivityForResult(Payment,0);
                }
            });

            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_filter:
                Intent intent = new Intent(this, Filter.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
