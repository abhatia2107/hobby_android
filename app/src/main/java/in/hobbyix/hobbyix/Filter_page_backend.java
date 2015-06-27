package in.hobbyix.hobbyix;

/**
 * Created by Diks on 6/19/2015.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Filter_page_backend extends Activity {

    //the progessdialog for progress bar
    private ProgressDialog pDialog;
    //url to get required guidelines
    private static String url_for_institute = "http://hobbyix.com/json/filter";
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
    static int length;
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values

    static ArrayList<HashMap<String, String>> guidelist = new ArrayList<HashMap<String, String>>();
    //JSONArray(inbuilt) to extract the JSONArray
    static JSONArray guidelines = null;
    static String[][] institute_list=new String[100][100];
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        institute_list=store_details();
        set_all_details();


    }*/

    public static    ArrayList<HashMap<String, String>> store_details() {

        ArrayList<HashMap<String, String>> aResultM = new    ArrayList<HashMap<String, String>>();
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
    /*public  void set_all_details()
    {
        int j;
        for(int i=0;i<Integer.valueOf(institute_list[0][0]);i++) {
            j=i+1;
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TAG_INSTITUTE, institute_list[j][0]);
            map.put("batch_category",institute_list[j][1] );
            map.put("venue_address", institute_list[j][2]);
            map.put("batch_price", institute_list[j][3]);
            map.put("batch_comment", institute_list[j][4]);

            //map.put(schedule_start_time, schedule_start_time);
            //map.put(schedule_end_time, schedule_end_time);

            guidelist.add(map);
        }
        Intent in = new Intent(getApplicationContext(),MainActivity.class);
        in.putExtra("guidelist",guidelist);
        in.putExtra("length",institute_list[0][0]);
        finish();
        startActivity(in);

    }*/
    public static    ArrayList<HashMap<String, String>> get_codes(String subcategory[], String locality[], int size_of_subcategory, int size_of_locality)
    {
        subcategory_filter=subcategory;
        locality_filter=locality;
        sub_length=size_of_subcategory;
        local_length=size_of_locality;
        ArrayList<HashMap<String, String>> x=store_details();
        return x;

    }


    static class Load_Filter_Details extends AsyncTask<String, String,   ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected    ArrayList<HashMap<String, String>> doInBackground(String... arg0) {
                   String url_end=null;
            // TODO Auto-generated method stub
            JSONArray jArr1= new JSONArray();
     for(int i=0;i<sub_length;i++){
         Log.e("jhjr",subcategory_filter[i]+" ");

            jArr1.put(subcategory_filter[i]);}

            JSONArray jArr2= new JSONArray();
          for(int i=0;i<local_length;i++){
          Log.e("dkfklkfslk",locality_filter[i]+"");
              jArr2.put(locality_filter[i]);}

           String url_for_sub,url_for_loc;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
           if(sub_length>0&&local_length>0)
            {
         url_for_sub=url_for_institute+"/";
                for(int i=0;i<sub_length;i++) {
                    if(i<sub_length-1)
                    url_for_sub=url_for_sub+subcategory_filter[i]+",";
                    else
                        url_for_sub=url_for_sub+subcategory_filter[i];
                }
                url_for_sub=url_for_sub+"/";
                url_for_loc=url_for_sub;
                for(int i=0;i<local_length;i++) {
                    if(i<local_length-1)
                    url_for_loc=url_for_loc+locality_filter[i]+",";
                    else
                        url_for_loc=url_for_loc+subcategory_filter[i];
                }


       url_end=url_for_loc+"/1/1";
            }
            else
            {
                if(local_length==0&&sub_length>0)
                {
                    url_for_sub=url_for_institute+"/subcategory/";
                    Log.e("dfhjkdhfdhfkfhdjghfjgf", ""+url_for_sub);
                    for(int i=0;i<sub_length;i++) {
                        if(i<sub_length-1)
                            url_for_sub=url_for_sub+subcategory_filter[i]+",";
                        else
                            url_for_sub=url_for_sub+subcategory_filter[i];
                    }
                    url_end=url_for_sub;
                }
                else
                {
                    if(local_length>0&&sub_length==0)
                    {
                        url_for_loc=url_for_institute+"/locality/";
                        for(int i=0;i<local_length;i++) {
                            if(i<local_length-1)
                                url_for_loc=url_for_loc+locality_filter[i]+",";
                            else
                                url_for_loc=url_for_loc+subcategory_filter[i];
                        }
                        url_end=url_for_loc;

                    }
                    else
                    {
                       /* Toast.makeText(getApplicationContext(), responseText,
                                Toast.LENGTH_LONG).show();*/
                    }
                }
            }


            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");
         //   for(i=0;i<sub_length)

                       Log.e("dfj",url_end+" ");
            JSONObject json = jparser.makeHttpRequest(url_end, "GET", params);
            if (json == null) {
                message = "No internet connection... please try later";
                length=-1;
                Log.v("tushita", "The Json Object was Null");
                return null;
            }
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    int k=0;
                    guidelines = json.getJSONArray("institute");

                    length=guidelines.length();
                    if(guidelines.length()==0)
                    {
                        length=0;
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_INSTITUTE, "no such institute");
                        guidelist.add(map);
                       /* map.put("batch_category","no such category");
                        map.put("venue_address", "no valuw for addres");
                        map.put("batch_price", batch_price);
                        map.put("batch_comment", batch_comment);
                        map.put("id", id);*/

                    }
                    else {
                        Log.e("length", ""+length);
                        for (int i = 0; i < guidelines.length(); i++) {
                            k = 0;
                            JSONObject c = guidelines.getJSONObject(i);

                            String name_of_institute = c.getString(TAG_INSTITUTE);
                            String batch_category = c.getString("subcategory");
                            String venue_address = c.getString("venue_address");
                            String batch_comment = c.getString("batch_comment");
                            Integer price = c.getInt("batch_single_price");
                            String batch_price = price.toString();
                            String id = c.getString("id");

                            Log.e("dhkajhdj", "" + name_of_institute + "");


                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(TAG_INSTITUTE, name_of_institute);
                            map.put("batch_category", batch_category);
                            map.put("venue_address", venue_address);
                            map.put("batch_price", batch_price);
                            map.put("batch_comment", batch_comment);
                            map.put("id", id);


                            //map.put(schedule_start_time, schedule_start_time);
                            //map.put(schedule_end_time, schedule_end_time);

                            guidelist.add(map);


                        }
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

            return guidelist;
        }

        @Override
        protected void onPostExecute(   ArrayList<HashMap<String, String>> result) {
            // TODO Auto-generated method stub

            // new MainActivity().populatePostList(result);
            super.onPostExecute(result);

            // pDialog.dismiss();
        }


    }
}


