package in.hobbyix.hobbyix;

/**
 * Created by Diks on 6/19/2015.
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Filter_page_backend {

    //the progessdialog for progress bar
    private ProgressDialog pDialog;
    //url to get required guidelines
    private static String url_for_institute = "http://192.168.137.1/Hobbyix/filter.php";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_INSTITUTE = "institute";
      public static String[] subcategory_filter=new String[100];
    static int sub_length;
    static int local_length;
    public static String[] locality_filter=new String[100];
    static String message = null;
    //object for JSONParser class
    static JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values

    ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();
    //JSONArray(inbuilt) to extract the JSONArray
    static JSONArray guidelines = null;
    static String[][] institute_list=new String[100][100];

    public static String[][] store_details() {

        String[][] aResultM = new String[0][];
        try {
            String params = null;
            Load_Filter_Details task = new Load_Filter_Details();
            task.execute(params);
            aResultM = task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResultM;

    }
    public static void get_codes(String subcategory[], String locality[], int size_of_subcategory, int size_of_locality)
    {
        subcategory_filter=subcategory;
        locality_filter=locality;
        sub_length=size_of_subcategory;
        local_length=size_of_locality;
        String x[][]=store_details();
    }


    static class Load_Filter_Details extends AsyncTask<String, String,String[][]> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected String[][] doInBackground(String... arg0) {

            // TODO Auto-generated method stub
            JSONArray jArr1= new JSONArray();
     for(int i=0;i<sub_length;i++){
         Log.e("jhjr",subcategory_filter[i]+" ");

            jArr1.put(subcategory_filter[i]);}

            JSONArray jArr2= new JSONArray();
          for(int i=0;i<local_length;i++){
          Log.e("dkfklkfslk",locality_filter[i]+"");
              jArr2.put(locality_filter[i]);}


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if(sub_length==0&&local_length>0)
            {
                params.add(new BasicNameValuePair("subcategories",""));
                params.add(new BasicNameValuePair("locality",jArr2.toString()));


            }
            else
            {
                if(local_length==0&&sub_length>0)
                {
                    params.add(new BasicNameValuePair("subcategories",jArr1.toString()));

                    params.add(new BasicNameValuePair("locality",""));

                }
                else
                {
                    if(local_length>0&&sub_length>0)
                    {
                        params.add(new BasicNameValuePair("subcategories",jArr1.toString()));

                        params.add(new BasicNameValuePair("locality",jArr2.toString()));
                    }
                    else
                    {

                    }
                }
            }


            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");

            JSONObject json = jparser.makeHttpRequest(url_for_institute, "POST", params);
            if (json == null) {
                message = "No internet connection... please try later";
                Log.v("tushita", "The Json Object was Null");
                return null;
            }
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    int k=0;
                    guidelines = json.getJSONArray("institutes");
                    for (int i = 0; i < guidelines.length(); i++) {
                        k=0;
                        JSONObject c = guidelines.getJSONObject(i);
                        // Integer id = c.getInt(TAG_ID);
                        String name_of_institute = c.getString(TAG_INSTITUTE);
                        String batch_category = c.getString("batch_category");
                       // String venue_address = c.getString("venue_address");
                        Integer price = c.getInt("batch_single_price");
                        String batch_price=price.toString();
                        // String schedule_start_time = c.getString("schedule_start_time");

                        // String schedule_end_time = c.getString("schedule_end_time");
                        Log.e("dhkajhdj", "" + name_of_institute + "");

                        institute_list[i][k]=name_of_institute;
                        k++;
                        institute_list[i][k]=batch_category;
                        k++;
                    //    institute_list[i][k]=venue_address;
                      //  k++;
                        institute_list[i][k]=batch_price;
                        k++;

                       /* HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_INSTITUTE, name_of_institute);

                        map.put("batch_category", batch_category);
                        map.put("venue_address", venue_address);
                        map.put("batch_price", batch_price);*/
                        //map.put(schedule_start_time, schedule_start_time);
                        //map.put(schedule_end_time, schedule_end_time);

                        // guidelist.add(map);
                    }
                    // Intent in = new Intent(getApplicationContext(),SQLtry.class);
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

            return institute_list;
        }

        @Override
        protected void onPostExecute(String[][] result) {
            // TODO Auto-generated method stub

            // new MainActivity().populatePostList(result);
            super.onPostExecute(result);

            // pDialog.dismiss();
        }


    }
}


