package in.hobbyix.hobbyix;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Institute_Details {

    private static String url_for_institute = "http://192.168.10.108/Hobbyix/displaying_institute_details.php";
    public String institute_list[][]=new String[100][5];
    JSONParser jsonParser = new JSONParser();
    JSONArray guidelines = null;


    public String[][] store_details() {

        String[][] aResultM = new String[0][];
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



    class Load_Institiute_Details extends AsyncTask<String, String,String[][]> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        public String[][] doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jsonParser.makeHttpRequest(url_for_institute, "GET", params);
            try{
                 guidelines = json.getJSONArray("institute");
                 institute_list[0][0]=Integer.toString(guidelines.length());

                 for (int i = 1; i <= guidelines.length(); i++) {
                     JSONObject c= guidelines.getJSONObject(i);
                     String name_of_institute = c.getString("institute");
                     String batch_category = c.getString("batch_category");
                     String batch_time=c.getString("batch_time");
                     String venue_address = c.getString("venue_address");
                     Integer price = c.getInt("batch_single_price");
                     String batch_price=price.toString();
                     institute_list[i][0]=name_of_institute;
                     institute_list[i][1]=batch_category;
                     institute_list[i][2]=batch_price;
                     institute_list[i][3]=batch_time;
                     institute_list[i][4]=venue_address;
                 }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return institute_list;
        }

        @Override
        protected void onPostExecute(String[][] result) {
            // TODO Auto-generated method stub
           super.onPostExecute(result);

        }
    }
}

