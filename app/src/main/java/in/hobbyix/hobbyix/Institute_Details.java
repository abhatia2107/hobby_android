package in.hobbyix.hobbyix;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Institute_Details extends Activity {

    //the progessdialog for progress bar
    private ProgressDialog pDialog;
    //url to get required guidelines
    private static String url_for_institute = "http://hobbyix.com/json/filter/categories/1/locations/1";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_INSTITUTE = "institute";
    String institute_list[][]=new String[100][100];
    String message = null;
    //object for JSONParser class
    JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values

    ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();
    //JSONArray(inbuilt) to extract the JSONArray
    JSONArray guidelines = null;
    String length;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        guidelist=store_details();
        set_all_details(guidelist);


    }
    public ArrayList<HashMap<String,String>> store_details() {

        ArrayList<HashMap<String,String>> aResultM = new ArrayList<HashMap<String,String>>();
        try {
            String params = null;
            Load_Institiute_Details task = new Load_Institiute_Details();
            task.execute(params);
            aResultM = task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResultM;

    }


    public  void set_all_details(ArrayList<HashMap<String,String>> guidelist)
    {


        Intent in = new Intent(getApplicationContext(),MainActivity.class);
        for(HashMap<String, String> map: guidelist) {
           // j=0;
            for(Map.Entry<String, String> mapEntry: map.entrySet()) {
                String key = mapEntry.getKey();
                //key=
                String value = mapEntry.getValue();
               // details[i][j]=mapEntry.getValue();
                Log.e("kjdf","   "+key+"     "+value+"    "   );
                //j++;

            }
            //i++;
        }
        in.putExtra("guidelist",guidelist);
        in.putExtra("length",length);
        finish();
        startActivity(in);

    }
    class Load_Institiute_Details extends AsyncTask<String, String, ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        public ArrayList<HashMap<String, String>> doInBackground(String... arg0) {

            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");

            JSONObject json = jparser.makeHttpRequest(url_for_institute, "GET", params);
            if (json == null) {

                message = "No internet connection... please try later";
                length="-1";
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TAG_INSTITUTE, "dfj");
                map.put("batch_category","kjdfkj" );
                Log.v("tushita", "The Json Object was Null");
                guidelist.add(map);
                return guidelist;
            }
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    guidelines = json.getJSONArray("institute");
                    int k;
                    int j=0;
                    length=Integer.toString(guidelines.length());
                    for (int i = 0; i < guidelines.length(); i++) {
                        k=0;
                        j=i+1;
                        JSONObject c = guidelines.getJSONObject(i);
                        // Integer id = c.getInt(TAG_ID);
                        String name_of_institute = c.getString(TAG_INSTITUTE);
                        String batch_category = c.getString("subcategory");
                        String venue_address = c.getString("venue_address");

                        String batch_comment = c.getString("batch_comment");
                        Integer price = c.getInt("batch_single_price");
                        String batch_price=price.toString();
                        String id=c.getString("id");
                       // String schedule_start_time = c.getString("schedule_start_time");

                       // String schedule_end_time = c.getString("schedule_end_time");
                        Log.e("dhkajhdj", "" + name_of_institute + "");
                       /*institute_list[j][k]=name_of_institute;
                        k++;
                        institute_list[j][k]=batch_category;
                        k++;
                        institute_list[j][k]=venue_address;
                        k++;
                        institute_list[j][k]=batch_price;
                        k++;
                        institute_list[j][k]=batch_comment;
                        k++;
                        institute_list[j][k]=id;
                        k++;*/

                       /* HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_INSTITUTE, name_of_institute);

                        map.put("batch_category", batch_category);
                        map.put("venue_address", venue_address);
                        map.put("batch_price", batch_price);*/
                        //map.put(schedule_start_time, schedule_start_time);
                        //map.put(schedule_end_time, schedule_end_time);

                       // guidelist.add(map);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_INSTITUTE, name_of_institute);
                        map.put("batch_category",batch_category );
                        map.put("venue_address", venue_address);
                        map.put("batch_price", batch_price);
                        map.put("batch_comment", batch_comment);
                        map.put("id", id);


                        //map.put(schedule_start_time, schedule_start_time);
                        //map.put(schedule_end_time, schedule_end_time);

                        guidelist.add(map);
                    }
                    //  Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //startActivity(in);

                } else {
                    Log.v("tush", "success was 0");
                    //Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //finish();
                    //startActivity(in);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return guidelist;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            // TODO Auto-generated method stub

           super.onPostExecute(result);

           // pDialog.dismiss();
        }


    }
}

