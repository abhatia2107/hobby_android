package in.hobbyix.hobbyix;

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

/**
 * Created by Diks on 6/19/2015.
 */
public class Order_backend {

        //the progessdialog for progress bar
        private ProgressDialog pDialog;
        //url to get required guidelines
        private static String url_for_institute = "http://192.168.137.1/Hobbyix/orders.php";
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



        static class Load_Filter_Details extends AsyncTask<String, String,String[][]> {
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();

            }

            @Override
            protected String[][] doInBackground(String... arg0) {

                // TODO Auto-generated method stub

                String email=LoginActivity.email_person;

                            List<NameValuePair> params = new ArrayList<NameValuePair>();

                           params.add(new BasicNameValuePair("email",email));




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
                        institute_list[0][0]=Integer.toString(guidelines.length());
                        int j=0;
                        for (int i = 0; i < guidelines.length(); i++) {
                            k=0;
                            JSONObject c = guidelines.getJSONObject(i);
                            // Integer id = c.getInt(TAG_ID);
                            String payment = c.getString("payment");
                            String booking_date = c.getString("booking_date");
                             String no_of_sessions = c.getString("no_of_sessions");
                            int order_id = c.getInt("order_id");
                            String created_at=c.getString("created_at");
                            // String schedule_start_time = c.getString("schedule_start_time");

                            // String schedule_end_time = c.getString("schedule_end_time");

                              j=i+1;
                            institute_list[j][k]=payment;
                            k++;
                            institute_list[j][k]=booking_date;
                            k++;

                            institute_list[j][k]=no_of_sessions;
                            k++;
                            institute_list[j][k]=Integer.toString(order_id);
                            k++;
                            institute_list[j][k]=created_at;
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



