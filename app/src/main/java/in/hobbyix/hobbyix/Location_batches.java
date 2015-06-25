package in.hobbyix.hobbyix;

import android.os.AsyncTask;
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
    private static String url_for_locality = "http://hobbyix.com/json/localities";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_LOCALITY = "localities";
       static int length;
    static String message = null;
    //object for JSONParser class
    static JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values
    static ArrayList<HashMap<String,String>> guidelist=new ArrayList<HashMap<String, String>>();
    //JSONArray(inbuilt) to extract the JSONArray
    static JSONArray localitylines = null;
    TextView institute_name;
    //-----------------------------------------------------------------------------------------------------------------------//
    public static ArrayList<HashMap<String, String>>  store_details_of_locality() {

        ArrayList<HashMap<String, String>>  aResultM = new ArrayList<HashMap<String, String>>();
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
    static class LoadAllLOCALITY extends AsyncTask<String, String, ArrayList<HashMap<String, String>> > {


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
        protected ArrayList<HashMap<String, String>>  doInBackground(String... arg0) {

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
                    length=localitylines.length();
                   int j;
                    locality_details_list[0]=Integer.toString(localitylines.length());
                    for(int i=0;i<localitylines.length();i++)
                    {
                        j=i+1;
                     JSONObject c=  localitylines.getJSONObject(i);

                        // Integer id = c.getInt(TAG_ID);
                        String name_of_locality = c.getString("locality");

                        String id=c.getString("id");


                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("locality", name_of_locality);
                        map.put("id",id );
                        guidelist.add(map);



                        //locality_details_list[j][0] = name_of_locality;
                        //locality_details_list[j][1] = id_local;
                    }
                }
                    } catch (JSONException e1) {
                e1.printStackTrace();
            }
            //  Intent in = new Intent(getApplicationContext(),SQLtry.class);
                    //in.putExtra("guidelist",guidelist);
                    //startActivity(in);


            return guidelist;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>>  result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            // pDialog.dismiss();
        }

    }
}




