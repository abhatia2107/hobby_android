package in.hobbyix.hobbyix;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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

    public String[][] store_details_of_subcategory() {

        String[][] aResultM = new String[400][2];
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
    class LoadAllSubcategory extends AsyncTask<String, String, String[][]> {


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
        protected String[][] doInBackground(String... arg0) {

            // TODO Auto-generated method stub

            List<NameValuePair> params = new ArrayList<NameValuePair>();


            Log.v("tushita", "The Json Object was Nukjcxvxcjvkcxvkcvll");
            String subcategory[][] = new String[0][0];
            JSONObject json = jparser.makeHttpRequest(url_for_subcategory, "GET", params);
            if (json == null) {
                message = "No internet connection... please try later";
                Log.v("tushita", "The Json Object was Null");
                return null;
            }
            try {

                Log.e("jkjhkjgdh", "success");

                subcategorylines = json.getJSONArray("subcategories");
                subcategory = new String[ subcategorylines.length()+ 100][3];
                subcategory[0][0] = Integer.toString(subcategorylines.length());
                Log.e("djfjk", "ooio" + subcategory[0][0] + "");
                int j;


                for (int i = 0; i < subcategorylines.length(); i++) {

                    j = i + 1;

                    //subcategorylines = json.getJSONArray(Integer.toString(i));
                    JSONObject c = subcategorylines.getJSONObject(i);

                    Integer id = c.getInt("id");
                    String name_of_subcategory = c.getString("subcategory");
                    String id_sub = Integer.toString(id);
                    Log.e("dhkajhdj", "" + name_of_subcategory + "");
                    subcategory[j][0] = name_of_subcategory;
                    subcategory[j][1] = id_sub;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }


            //  Intent in = new Intent(getApplicationContext(),SQLtry.class);
            //in.putExtra("guidelist",guidelist);
            //startActivity(in);


            return subcategory;
        }


        @Override
        protected void onPostExecute(String[][] result) {
            // TODO Auto-generated method stub

         super.onPostExecute(result);

           // pDialog.dismiss();
        }

    }
}


