package in.hobbyix.hobbyix;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> params) {

        // Making HTTP request
        try {

            // check for request method
            if(method == "POST"){
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if(method == "GET"){
                // request method is GET
                Log.e("dikshasada","djfjdjfshfkd");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                Log.e("Navneet","URLS is "+url);
                HttpResponse httpResponse = httpClient.execute(httpGet);

                Log.e("Navneet","Not in of the GET");
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();


            }
        } catch (UnsupportedEncodingException e) {
            Log.e("Navneet","Encoding Exception");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            Log.e("Navneet","Client Protocol Exception");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Navneet","IO Exception");
            e.printStackTrace();
        }

        try {
            Log.e("Navneet","Hey man are you crazy2122");
            if(is==null){
                Log.e("Navneet","Hey man are you crazy");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(

                    is, "iso-8859-1"), 8);
            //Log.e("Navneet","Hey man are you carzy2");
            StringBuilder sb = new StringBuilder();
            String line = null;
            //Log.e("Navneet","Hey man are you carzy3");
			/*if(is !=null){
                while ((line = in.readLine()) != null) {
                sb.append(line + "\n");
                x = sb.toString();
            }
            responseJsonData = new String(x);

            }*/
            if(is!=null){
                while ((line = reader.readLine()) != null) {
                    Log.v("Navneetr","THis is not jullll");
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
                Log.e("djhfj",""+json+"");
            }else{
                Log.v("My ererer","THis is null");
                json = "";
            }

        } catch (Exception e) {
            jObj=null;
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            jObj = null;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;

    }
}

