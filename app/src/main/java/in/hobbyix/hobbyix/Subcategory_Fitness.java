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
 * Created by Diks on 6/4/2015.
 */
public class Subcategory_Fitness  {
    //url to get required guidelines
    private static String url_for_subcategory = "http://hobbyix.com/json/subcategories";
    // desc of all important strings : names of columns
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_SUBCATEGORY = "subcategory";

    static String message = null;
    //object for JSONParser class
    static JSONParser jparser = new JSONParser();
    //button to start the syncing of databases
    Button btn;
    // ArrayList of HashMaps to store the JSONArray of mapped values
    static ArrayList<HashMap<String,String>> guidelist=new ArrayList<HashMap<String, String>>();
    //JSONArray(inbuilt) to extract the JSONArray
    static JSONArray subcategorylines = null;
    static int length;
    TextView institute_name;
    //-----------------------------------------------------------------------------------------------------------------------//

    public static ArrayList<HashMap<String,String>> store_details_of_subcategory() {

        ArrayList<HashMap<String,String>> aResultM = new   ArrayList<HashMap<String,String>>();
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
    static class LoadAllSubcategory extends AsyncTask<String, String,   ArrayList<HashMap<String,String>>> {


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
        protected   ArrayList<HashMap<String,String>> doInBackground(String... arg0) {

            // TODO Auto-generated method stub

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");
            String subcategory[] = new String[0];
            JSONObject json = jparser.makeHttpRequest(url_for_subcategory, "GET", params);
            if(json==null){
                message = "No internet connection... please try later";
                length=-1;
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
                    length=subcategorylines.length();
                    subcategory[0]=Integer.toString(subcategorylines.length());
                    Log.e("djfjk","ooio"+subcategory[0]+"");
                    int j;
                    for(int i=0;i<subcategorylines.length();i++)
                    {
                        j=i+1;
                        JSONObject c =  subcategorylines.getJSONObject(i);

                        // Integer id = c.getInt(TAG_ID);
                        String name_of_subcategory = c.getString("subcategory");
                        String id=c.getString("id");

                        Log.e("dhkajhdj",""+name_of_subcategory+"");
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("subcategory", name_of_subcategory);
                        map.put("id",id );
                        guidelist.add(map);


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


