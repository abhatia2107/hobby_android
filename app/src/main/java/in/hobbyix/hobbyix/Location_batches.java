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
 * Created by Diks on 6/6/2015.
 */

/**
 * Created by Diks on 6/4/2015.
 */
public class Location_batches  {
    //url to get required guidelines
    private static String url_for_locality = "http://192.168.137.1/Hobbyix/displaying_localities.php";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_LOCALITY = "localities";

    String message = null;
    //object for JSONParser class
    JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values
    ArrayList<HashMap<String,String>> localitylist;
    //JSONArray(inbuilt) to extract the JSONArray
    JSONArray localitylines = null;
    TextView institute_name;
    //-----------------------------------------------------------------------------------------------------------------------//
    public String[] store_details_of_locality() {

        String[] aResultM = new String[30];
        try {
            String params = null;
            LoadAllLOCALITY task = new LoadAllLOCALITY();
            task.execute(params);
            aResultM = task.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aResultM;

    }


    //======================================================Class LoadAllGuidelines==============================================//
    class LoadAllLOCALITY extends AsyncTask<String, String, String[]> {


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
                 String locality_details_list[] = new String[0];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");

            JSONObject json = jparser.makeHttpRequest(url_for_locality, "GET", params);
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

                    localitylines =  json.getJSONArray(TAG_LOCALITY );
                    locality_details_list=new String[localitylines.length()+1];
                    Log.e("jjkfdsffffkkk",localitylines.length()+"");
                   int j;
                    locality_details_list[0]=Integer.toString(localitylines.length());
                    for(int i=0;i<localitylines.length();i++)
                    {
                        j=i+1;
                     JSONObject c=  localitylines.getJSONObject(i);

                        // Integer id = c.getInt(TAG_ID);
                        String name_of_locality = c.getString("locality");

                        Log.e("dhkajhdj",""+name_of_locality+"");

                    locality_details_list[j]=name_of_locality;

                    }
                    //  Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //startActivity(in);

                }else{

                    //Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //finish();
                    //startActivity(in);
                }

            }catch(JSONException e){
                e.printStackTrace();
            }

            return locality_details_list;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            // pDialog.dismiss();
        }

    }
}




