package in.hobbyix.hobbyix;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity  {

PostItemListAdapter adapter;
  int length;
    int used;
      String details[] =new String[10000];
    private List<PostItems> PostItemsList = new ArrayList<PostItems>();
    ListView postlist;
    int k;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postlist=(ListView)findViewById(R.id.PostItemListView);
        populatePostItemsList();
        populatePostItemsListView();
        postlist.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                customLoadMoreDataFromApi(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
            }
        });


    }
    public void customLoadMoreDataFromApi(int offset) {

        Log.e("dikhsa", "diksha");
        int i;
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter

            if (length>10){
                for (i = used; i < used + 10; i++) {
                    PostItemsList.add(new PostItems(details[k+1], details[k+2], details[k+3], details[k+4], details[k+5], "Credit", "Book Now",details[k]));
                    // PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
                    k=k+6;
                    // PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
                }
                length=length-10;
                used = used + 10;
            } else {
                if (length  > 0 && length <= 10) {
                    for (i = used; i <  length+used; i++) {
                        PostItemsList.add(new PostItems(details[k+1], details[k+2], details[k+3], details[k+4], details[k+5], "Credit", "Book Now",details[k]));
                        // PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
                        k=k+6;
                    }
                    used=used+length;
                    length=0;


                }



            }
            Log.e("dkjfjds", "  " + length + "   " + used);
            populatePostItemsListView();


    }

    private void populatePostItemsList() {
        Intent in=getIntent();
        length=Integer.valueOf(in.getExtras().getString("length"));

        ArrayList<HashMap<String, String>> arl = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("guidelist");

        Log.e("dskjfhds"," "+length);
        HashMap<String, String>[] mylist;
        int i=0,k=0;
        int j;
        if(length==0){
            PostItemsList.add(new PostItems("no such value for this combination","","","","", "", "",""));

        }
        else {

            for (HashMap<String, String> map : arl) {

                for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                    String key = mapEntry.getKey();
                    //key=
                    String value = mapEntry.getValue();
                    details[k] = value;
                    Log.e("kjdf", "   " + key + "     " + value + "    " + i + "   " + details[k]);

                    k++;
                }

            }
            k = 0;
            used = 0;
            if (length > 10) {
                for (i = 0; i < 10; i++) {
                    PostItemsList.add(new PostItems(details[k + 1], details[k + 2], details[k + 3], details[k + 4], details[k + 5], "Credit", "Book Now", details[k]));
                    // PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
                    k = k + 6;
                }

                used = 10;

                length = length - 10;
                Log.e("dkjfjds", "  " + length + "   " + used);
            } else {
                if (length > 0 && length <= 10) {
                    for (i = 0; i < length; i++) {
                        PostItemsList.add(new PostItems(details[k + 1], details[k + 2], details[k + 3], details[k + 4], details[k + 5], "Credit", "Book Now", details[k]));
                        // PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
                        k = k + 6;

                        // PostItemsList.add(new PostItems("Name of Institute"+i,"ClassType"+i,"Address"+i,"Timings"+i,"Fees"+i,"Credit"+i,"Book Now"));
                    }
                    used = length;
                    length = 0;

                }

            }
        }
    }

    private void populatePostItemsListView() {
         adapter = new PostItemListAdapter(this,
                R.layout.item_view, (ArrayList<PostItems>) PostItemsList);
        ListView PostItemsListView = (ListView)findViewById(R.id.PostItemListView);
        PostItemsListView.setAdapter(adapter);
    }
    private class PostItemListAdapter extends  ArrayAdapter<PostItems> {

        public final ArrayList<PostItems> PostItemList;

        public PostItemListAdapter(Context context, int textViewResourceId,
                                   ArrayList<PostItems> PostItemList) {

            super(context, textViewResourceId, PostItemList);

            this.PostItemList = new ArrayList<PostItems>();
            this.PostItemList.addAll(PostItemList);
        }

        private class ViewHolder {
            ImageView imageView;
            TextView txtTitle;
            TextView txtDesc;
            TextView InstituteName;
            TextView InstituteClassType;
            TextView InstituteTimings;
            TextView InstituteAddress;
            TextView InstituteFees;
            TextView InstituteCredit;
            Button BookNowButton;
            String id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            ViewHolder holder;
            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.item_view, null);
                holder = new ViewHolder();


                holder.InstituteName = (TextView) convertView.findViewById(R.id.item_NameTextView);
                holder.InstituteClassType = (TextView) convertView.findViewById(R.id.item_ClassTypeTextView);
                holder.InstituteTimings = (TextView) convertView.findViewById(R.id.item_TimingsTextView);
                holder.InstituteAddress = (TextView) convertView.findViewById(R.id.item_AddressTextView);
                holder.InstituteFees = (TextView) convertView.findViewById(R.id.item_FessTextView);
                holder.InstituteCredit = (TextView) convertView.findViewById(R.id.CreditTextView);
                holder.BookNowButton = (Button) convertView.findViewById(R.id.item_book_now_button);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final PostItems CurrentPost = PostItemsList.get(position);
            // Log.e("dfjsd", CurrentPost.getName());

            holder.InstituteName.setText(CurrentPost.getName());
            holder.InstituteClassType.setText(CurrentPost.getClassType());
            holder.InstituteTimings.setText(CurrentPost.getTimings());
            holder.InstituteAddress.setText(CurrentPost.getAddress());
            holder.InstituteFees.setText(CurrentPost.getFees());
            holder.InstituteCredit.setText(CurrentPost.getCredit());
            holder.BookNowButton.setText(CurrentPost.getBookNow());
            holder.InstituteName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("lfkdjsflk", "popopo");
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this, SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivity(BookNowSamplePageIntent);
                }
            });
            holder.InstituteClassType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this, SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivity(BookNowSamplePageIntent);
                }
            });
            holder.InstituteTimings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this, SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivity(BookNowSamplePageIntent);
                }
            });
            holder.InstituteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this, SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivity(BookNowSamplePageIntent);
                }
            });
            holder.InstituteFees.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this, SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivity(BookNowSamplePageIntent);
                }
            });
            holder.InstituteCredit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(MainActivity.this, SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivity(BookNowSamplePageIntent);
                }
            });
            holder.BookNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent BookNowSamplePageIntent = new Intent(getApplicationContext(), SamplePage.class);
                    BookNowSamplePageIntent.putExtra("id", CurrentPost.getId());
                    startActivityForResult(BookNowSamplePageIntent, 0);
                }
            });

            return convertView;

        }
    }
            //  SessionManagement session;


            @Override
            public boolean onCreateOptionsMenu ( final Menu menu){
                // Inflate the menu items for use in the action bar
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_main, menu);
                // Associate searchable configuration with the SearchView
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setIconifiedByDefault(true);
       /* searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide action item
                if (menu != null) {
                    menu.findItem(R.id.action_login).setVisible(false);
                    menu.findItem(R.id.action_filter).setVisible(false);
                }
            }
        });
       searchView .setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
              //  adapter.getFilter().filter("");
                // re-show the action button
                if (menu != null) {
                    menu.findItem(R.id.action_filter).setVisible(true);
                    menu.findItem(R.id.action_login).setVisible(true);
                    menu.findItem(R.id.action_filter).setVisible(true);
                }
                return false;
            }
        });*/

                SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        // this is your adapter that will be filtered
                        // myAdapter.getFilter().filter(newText);
                        //System.out.println("on text chnge text: "+newText);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        String institute[][] = new String[200][100];
                        institute = Search_Backend.store_details(query);
                        Log.e("jhjk", " " + institute[0][0]);
                        // this is your adapter that will be filtered
                        // myAdapter.getFilter().filter(query);
                        ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();
                        int j;
                        for (int i = 0; i < Integer.valueOf(institute[0][0]); i++) {
                            j = i + 1;
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("institute", institute[j][0]);
                            map.put("batch_category", institute[j][1]);
                            map.put("venue_address", institute[j][2]);
                            map.put("batch_price", institute[j][3]);
                            map.put("batch_comment", institute[j][4]);
                            map.put("id", institute[j][5]);
                            //map.put(schedule_start_time, schedule_start_time);
                            //map.put(schedule_end_time, schedule_end_time);

                            guidelist.add(map);
                        }
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        in.putExtra("guidelist", guidelist);
                        in.putExtra("length", institute[0][0]);
                        finish();
                        startActivity(in);


                        System.out.println("on query submit: " + query);
                        return true;
                    }
                };
                searchView.setOnQueryTextListener(textChangeListener);


                return true;

            }
            @Override
            public boolean onOptionsItemSelected (MenuItem item){
                switch (item.getItemId()) {


                    case R.id.action_filter:
                        Intent FilterIntent = new Intent(this, FilterPage.class);
                        this.startActivity(FilterIntent);
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
                    case R.id.action_login:
                        Intent LoginIntent = new Intent(this, LoginActivity.class);
                        this.startActivity(LoginIntent);
                        break;
                    default:
                        return super.onOptionsItemSelected(item);
                }
                return true;
            }
            public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
                // The minimum amount of items to have below your current scroll position
                // before loading more.
                private int visibleThreshold = 5;
                // The current offset index of data you have loaded
                private int currentPage = 0;
                // The total number of items in the dataset after the last load
                private int previousTotalItemCount = 0;
                // True if we are still waiting for the last set of data to load.
                private boolean loading = true;
                // Sets the starting page index
                private int startingPageIndex = 0;

                public EndlessScrollListener() {
                }

                public EndlessScrollListener(int visibleThreshold) {
                    this.visibleThreshold = visibleThreshold;
                }

                public EndlessScrollListener(int visibleThreshold, int startPage) {
                    this.visibleThreshold = visibleThreshold;
                    this.startingPageIndex = startPage;
                    this.currentPage = startPage;
                }

                // This happens many times a second during a scroll, so be wary of the code you place here.
                // We are given a few useful parameters to help us work out if we need to load some more data,
                // but first we check if we are waiting for the previous load to finish.
                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    // If the total item count is zero and the previous isn't, assume the
                    // list is invalidated and should be reset back to initial state
                    if (totalItemCount < previousTotalItemCount) {
                        this.currentPage = this.startingPageIndex;
                        this.previousTotalItemCount = totalItemCount;
                        if (totalItemCount == 0) {
                            this.loading = true;
                        }
                    }
                    // If it’s still loading, we check to see if the dataset count has
                    // changed, if so we conclude it has finished loading and update the current page
                    // number and total item count.
                    if (loading && (totalItemCount > previousTotalItemCount)) {
                        loading = false;
                        previousTotalItemCount = totalItemCount;
                        currentPage++;
                    }

                    // If it isn’t currently loading, we check to see if we have breached
                    // the visibleThreshold and need to reload more data.
                    // If we do need to reload some more data, we execute onLoadMore to fetch the data.
                    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                        onLoadMore(currentPage + 1, totalItemCount);
                        loading = true;
                    }
                }

                // Defines the process for actually loading more data based on page
                public abstract void onLoadMore(int page, int totalItemsCount);

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    // Don't take any action on changed
                }
            }
        }