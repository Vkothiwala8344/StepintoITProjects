package com.stepintoit.vkoth.calculatorapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetJsonActivity extends AppCompatActivity {

    TextView tvJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_json);

        tvJsonData = (TextView) findViewById(R.id.tv_jsondata);
        new JsonTask().execute();

    }

    private class JsonTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            String response = "";

            try {

                // This is getting the url from the string we passed in
                URL url = new URL("https://my-json-server.typicode.com/Vkothiwala8344/StepintoITProjects/db");

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setRequestMethod("GET");


                int statusCode = urlConnection.getResponseCode();
                Log.d("statusLog", Integer.toString(statusCode));
                if (statusCode == 200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    response = getStringFromInputStream(inputStream);

                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error

                }

            } catch (Exception e) {
                //Log.d(TAG, e.getLocalizedMessage());
            }

            return response;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //  pbLogin.setVisibility(View.INVISIBLE);
            tvJsonData.setText(response);
            if (response != null && !response.isEmpty()) {

                try {

                    JSONObject tokenJson = new JSONObject(response);

                    JSONArray resArray = tokenJson.getJSONArray("productData");


                    ArrayList<ProductModel> postProductModelArrayList = new ArrayList<>();


                    for (int i = 0; i < resArray.length(); i++) {

                        ProductModel productModel = new ProductModel();

                        JSONObject productObject = resArray.getJSONObject(i);


                        String productName = productObject.getString("name");
                        productModel.setProductName(productName);
                        Log.d("getjsonActivity", "product name = " + productName);

                        ArrayList<String> imageList = new ArrayList<>();
                        JSONArray imageArray = productObject.getJSONArray("images");
                        for (int k = 0; k < imageArray.length(); k++) {
                            Log.d("getjsonActivity", "imageURL=" + imageArray.get(k));
                            imageList.add(imageArray.getString(k));
                        }
                        productModel.setProductImage(imageList);

                        ArrayList<String> tagList = new ArrayList<>();
                        JSONArray tagsArray = productObject.getJSONArray("tags");
                        for (int j = 0; j < tagsArray.length(); j++) {
                            Log.d("getjsonActivity", "tags=" + tagsArray.get(j));
                            tagList.add(tagsArray.getString(j));
                        }
                        productModel.setTags(tagList);

                        WarehouseLocationModel warehouseLocationModel = new WarehouseLocationModel();
                        JSONObject whObj = productObject.getJSONObject("warehouseLocation");
                        double latitude = whObj.getDouble("latitude");
                        double longitude = whObj.getDouble("longitude");
                        warehouseLocationModel.setLatitude(latitude);
                        warehouseLocationModel.setLongitude(longitude);

                        productModel.setWarehouseLocationModel(warehouseLocationModel);

                        Log.d("getjsonActivity", "Location: Latitude =" + latitude + " longitude = " + longitude);

                        postProductModelArrayList.add(productModel);
                    }

                    for(int f=0;f<postProductModelArrayList.size();f++) {

                        ProductModel productModel = postProductModelArrayList.get(f);

                        String productName = productModel.getProductName();
                        Log.d("ModelData",Integer.toString(f+1) +">---ProductName = "+productName+"\n");

                        for(int a=0;a<productModel.getProductImage().size();a++) {
                            String images = productModel.getProductImage().get(a);
                            Log.d("ModelData","Images : "+images+"\n");
                        }

                        for(int b=0;b<productModel.getTags().size();b++) {
                            String tags = productModel.getTags().get(b);
                            Log.d("ModelData","Tags : "+tags+"\n");
                        }

                        double lat = productModel.getWarehouseLocationModel().getLatitude();
                        double longi = productModel.getWarehouseLocationModel().getLongitude();

                        Log.d("ModelData","Location : Latitude = "+lat+" Longitude = "+longi+"\n");
                        Log.d("ModelData","------------------------------------------------------\n");



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(GetJsonActivity.this, "Json data fetching failed", Toast.LENGTH_SHORT).show();
            }
        }

        public String getStringFromInputStream(InputStream stream) throws IOException {
            int n = 0;
            char[] buffer = new char[1024 * 4];
            InputStreamReader reader = new InputStreamReader(stream, "UTF8");
            StringWriter writer = new StringWriter();
            while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
            return writer.toString();
        }
    }
}
