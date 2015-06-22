package in.hobbyix.hobbyix;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

/**
 * Created by Diks on 6/21/2015.
 */




/**
 * Created by Diks on 6/19/2015.
 */

public class Search_Backend  {

        //the progessdialog for progress bar
        private ProgressDialog pDialog;
        //url to get required guidelines
        private static String url_for_institute = "http://hobbyix.com/json/filter/search";
        // desc of all important strings : names of columns
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_INSTITUTE = "institute";
        public static String[] subcategory_filter=new String[100];
        static int sub_length;
        static int local_length;
        public static String[] locality_filter=new String[100];
        static String message = null;
    static String get_keyword;
        //object for JSONParser class
        static JSONParser jparser = new JSONParser();
        //button to start the syncing of databases
        Button btn;
        // ArrayList of HashMaps to store the JSONArray of mapped values

        ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();
        //JSONArray(inbuilt) to extract the JSONArray
        static JSONArray guidelines = null;
       static   String[][] institute_list=new String[100][100];
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        get_keyword=  i.getExtras().getString("guidelist");
        institute_list=store_details();
        set_all_details(institute_list);


    }*/
        public static String[][] store_details(String x) {
            get_keyword=x;

            String[][] aResultM = new String[100][100];
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

    /*public  void set_all_details(String[][]institute)
    {
        int j;
       for(int i=0;i<Integer.valueOf(institute[0][0]);i++) {
           j=i+1;
           HashMap<String, String> map = new HashMap<String, String>();
           map.put(TAG_INSTITUTE, institute[j][0]);
           map.put("batch_category",institute[j][1] );
           map.put("venue_address", institute[j][2]);
           map.put("batch_price", institute[j][3]);
           map.put("batch_comment", institute[j][4]);
           map.put("id", institute[j][5]);
           //map.put(schedule_start_time, schedule_start_time);
           //map.put(schedule_end_time, schedule_end_time);

           guidelist.add(map);
       }
        Intent in = new Intent(getApplicationContext(),MainActivity.class);
        in.putExtra("guidelist",guidelist);
        in.putExtra("length",institute[0][0]);
        finish();
        startActivity(in);

    }*/



        static class Load_Filter_Details extends AsyncTask<String, String,String[][]> {
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();

            }

            @Override
            protected String[][] doInBackground(String... arg0) {

                // TODO Auto-generated method stub



                List<NameValuePair> params = new ArrayList<NameValuePair>();

                    params.add(new BasicNameValuePair("keyword",get_keyword));



                Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");

                JSONObject json = jparser.makeHttpRequest(url_for_institute, "GET", params);
                if (json == null) {
                    message = "No internet connection... please try later";
                    Log.v("tushita", "The Json Object was Null");
                    return null;
                }
                try {
                    int success = json.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        int k=0;
                        int j;
                        guidelines = json.getJSONArray("institute");
                        institute_list[0][0]=Integer.toString(guidelines.length());
                        for (int i = 0; i < guidelines.length(); i++) {
                            k=0;
                            j=i+1;
                            JSONObject c = guidelines.getJSONObject(i);
                            String name_of_institute = c.getString("institute");
                            String batch_category = c.getString("subcategory");
                            String venue_address = c.getString("venue_address");
                            String venue_landmark = c.getString("venue_landmark");
                            String locality=c.getString("locality");
                            String location=c.getString("location");
                       /* String =c.getString("");
                        String =c.getString("");
                        String =c.getString("");*/
                            Integer price = c.getInt("batch_single_price");
                            Integer shower_room = c.getInt("shower_room");
                            Integer air_conditioning = c.getInt("air_conditioning");
                            Integer cafe = c.getInt("cafe");
                            Integer changing_room = c.getInt("changing_room");
                            String batch_price=price.toString();
                            String batch_shower_room=shower_room.toString();
                            String batch_air_conditioning=air_conditioning.toString();
                            String batch_cafe=cafe.toString();
                            String batch_changing_room =changing_room.toString();
                            String batch_comment=c.getString("batch_comment");
                            String id=c.getString("id");
                            // String schedule_start_time = c.getString("schedule_start_time");

                            // String schedule_end_time = c.getString("schedule_end_time");

                            institute_list[j][k]=name_of_institute;
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
                            k++;

                           /* institute_list[j][k]=venue_landmark;
                            k++;
                            institute_list[j][k]=locality;
                            k++;
                            institute_list[j][k]=location;
                            k++;
                            institute_list[j][k]=batch_price;
                            k++;
                            institute_list[j][k]=batch_shower_room;
                            k++;
                            institute_list[j][k]=batch_air_conditioning;
                            k++;
                            institute_list[j][k]=batch_cafe;
                            k++;
                            institute_list[j][k]=batch_changing_room;
                            k++;
                            institute_list[j][k]=c.getString("batch_tagline");
                            k++;
                            institute_list[j][k]=c.getString("venue_contact_no");
                            k++;*/


                        }
                        //tore_all_details();

                    } else {
                        Log.v("tush", "success was 0");

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



