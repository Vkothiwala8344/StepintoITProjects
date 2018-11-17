package com.stepintoit.vkoth.calculatorapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.stepintoit.vkoth.calculatorapplication.data.APIClient;
import com.stepintoit.vkoth.calculatorapplication.data.APIInterface;
import com.stepintoit.vkoth.calculatorapplication.adapter.ProductAdapter;
import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.data.MySharedPreference;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    TextView tvJsonData;
    private ArrayList<ProductModel> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

      //  new JsonTask().execute();
        getProductData();
    }

    boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

//    private class JsonTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            String response = "";
//
//            try {
//
//                // This is getting the url from the string we passed in
//                URL url = new URL("https://my-json-server.typicode.com/Vkothiwala8344/StepintoITProjects/db");
//
//                // Create the urlConnection
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//
//                urlConnection.setRequestMethod("GET");
//
//
//                int statusCode = urlConnection.getResponseCode();
//                Log.d("statusLog", Integer.toString(statusCode));
//                if (statusCode == 200) {
//
//                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                    response = getStringFromInputStream(inputStream);
//
//                    // From here you can convert the string to JSON with whatever JSON parser you like to use
//                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
//                } else {
//                    // Status code is not 200
//                    // Do something to handle the error
//
//                }
//
//            } catch (Exception e) {
//                //Log.d(TAG, e.getLocalizedMessage());
//            }
//
//            return response;
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String response) {
//            super.onPostExecute(response);
//            //  pbLogin.setVisibility(View.INVISIBLE);
//            // tvJsonData.setText(response);
//            if (response != null && !response.isEmpty()) {
//
//                try {
//
//                    JSONObject tokenJson = new JSONObject(response);
//
//                    JSONArray resArray = tokenJson.getJSONArray("productData");
//
//
//                    ArrayList<ProductModel> postProductModelArrayList = new ArrayList<>();
//
//
//                    for (int i = 0; i < resArray.length(); i++) {
//
//                        ProductModel productModel = new ProductModel();
//
//                        JSONObject productObject = resArray.getJSONObject(i);
//
//
//                        String productName = productObject.getString("name");
//                        productModel.setProductName(productName);
//
//
//                        Log.d("getjsonActivity", "product name = " + productName);
//
//                        ArrayList<String> imageList = new ArrayList<>();
//                        JSONArray imageArray = productObject.getJSONArray("images");
//                        for (int k = 0; k < imageArray.length(); k++) {
//                            Log.d("getjsonActivity", "imageURL=" + imageArray.get(k));
//                            imageList.add(imageArray.getString(k));
//                        }
//                        productModel.setProductImage(imageList);
//
//                        ArrayList<String> tagList = new ArrayList<>();
//                        JSONArray tagsArray = productObject.getJSONArray("tags");
//                        for (int j = 0; j < tagsArray.length(); j++) {
//                            Log.d("getjsonActivity", "tags=" + tagsArray.get(j));
//                            tagList.add(tagsArray.getString(j));
//                        }
//                        productModel.setTags(tagList);
//
//                        WarehouseLocationModel warehouseLocationModel = new WarehouseLocationModel();
//                        JSONObject whObj = productObject.getJSONObject("warehouseLocation");
//                        double latitude = whObj.getDouble("latitude");
//                        double longitude = whObj.getDouble("longitude");
//                        warehouseLocationModel.setLatitude(latitude);
//                        warehouseLocationModel.setLongitude(longitude);
//
//                        productModel.setWarehouseLocationModel(warehouseLocationModel);
//
//                        Log.d("getjsonActivity", "Location: Latitude =" + latitude + " longitude = " + longitude);
//
//
//                        productList.add(productModel);
//                        //  productList.add(productModel);
//                        // productList = postProductModelArrayList;
//                    }
//
//                    sendProductListToRecycler();
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            } else {
//                Toast.makeText(ProductActivity.this, "Json data fetching failed", Toast.LENGTH_SHORT).show();
//            }
//
//
//        }
//
//        public String getStringFromInputStream(InputStream stream) throws IOException {
//            int n = 0;
//            char[] buffer = new char[1024 * 4];
//            InputStreamReader reader = new InputStreamReader(stream, "UTF8");
//            StringWriter writer = new StringWriter();
//            while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
//            return writer.toString();
//        }
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itm_logout:

                MySharedPreference.getInstance(ProductActivity.this).deleteValue(MySharedPreference.KEY_TOKEN);
                //MySharedPreference.getInstance(CalculatorActivity.this).deleteValue(MySharedPreference.KEY_PASSWORD);
                startActivity(new Intent(ProductActivity.this, LoginActivity.class));
                finish();
                return true;

            case R.id.itm_favourite:

                ArrayList<ProductModel> favouriteList = new ArrayList<ProductModel>();
                Intent i = new Intent(ProductActivity.this, FavouriteProductActivity.class);

                for(int a=0;a<productList.size();a++)
                {
                 if(productList.get(a).isFavouriteFlag())
                 {
                     favouriteList.add(productList.get(a));
                 }
                }
                i.putExtra("favouriteList",favouriteList);

                startActivity(i);

                //Toast.makeText(getApplicationContext(),"favourite opened",Toast.LENGTH_SHORT).show();
        }
        return (super.onOptionsItemSelected(item));
    }

    void sendProductListToRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        productAdapter = new ProductAdapter(ProductActivity.this, productList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(ProductActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(productAdapter);
    }

    void getProductData()
    {
       APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<ArrayList<ProductModel>> call = apiInterface.getProductList();
        call.enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {

                productList = response.body();
             Log.d("ProductActivity","test = "+Integer.toString(productList.size()));
             for(int i=0;i<productList.size();i++)
             {
                 Log.d("ProductName","name="+productList.get(i).getName());
             }

             sendProductListToRecycler();
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }

}
