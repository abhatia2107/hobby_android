package in.hobbyix.hobbyix;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Diks on 6/4/2015.
 */
public class Subcategory_Fitness  {
    //url to get required guidelines
    private static String url_for_subcategory = "http://192.168.10.110/Hobbyix/displaying_subcategory_details.php";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_SUBCATEGORY = "subcategory";

    String message = null;
    //object for JSONParser class
    JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values
    ArrayList<HashMap<String,String>> subcategorylist;
    //JSONArray(inbuilt) to extract the JSONArray
    JSONArray subcategorylines = null;
    TextView institute_name;
    //-----------------------------------------------------------------------------------------------------------------------//

    public String[] store_details_of_subcategory() {

        String[] aResultM = new String[30];
        try {
            String params = null;
            LoadAllSubcategory task = new LoadAllSubcategory();
            task.execute(params);
            aResultM = task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResultM;

    }
    public void store(ArrayList<HashMap<String, String>> result)
{
    Log.e("ghg",""+result+"");
}

    //======================================================Class LoadAllGuidelines==============================================//
    class LoadAllSubcategory extends AsyncTask<String, String, String[]> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
           /* pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading all Products Please Wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*/

        }
        @Override
        protected String[] doInBackground(String... arg0) {

            // TODO Auto-generated method stub

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");
            String subcategory[] = new String[0];
            JSONObject json = jparser.makeHttpRequest(url_for_subcategory, "GET", params);
            if(json==null){
                message = "No internet connection... please try later";
                Log.v("tushita","The Json Object was Null");
                return null;
            }
            try
            {
                int success = json.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    Log.e("jkjhkjgdh","success");
                    subcategorylines =  json.getJSONArray("subcategories");

                    subcategory = new  String[subcategorylines.length()+1];
                    subcategory[0]=Integer.toString(subcategorylines.length());
                    Log.e("djfjk","ooio"+subcategory[0]+"");
                    int j;
                    for(int i=0;i<subcategorylines.length();i++)
                    {
                        j=i+1;
                     JSONObject c =  subcategorylines.getJSONObject(i);

                        // Integer id = c.getInt(TAG_ID);
                        String name_of_subcategory = c.getString(TAG_SUBCATEGORY);

                        Log.e("dhkajhdj",""+name_of_subcategory+"");
                           subcategory[j]=name_of_subcategory;


                    }
                    //  Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //startActivity(in);

                }else{
                    Log.v("tush","success was 0");
                    //Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //finish();
                    //startActivity(in);
                }

            }catch(JSONException e){
                e.printStackTrace();
            }

            return subcategory;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // TODO Auto-generated method stub
         super.onPostExecute(result);

           // pDialog.dismiss();
        }

    }
}


