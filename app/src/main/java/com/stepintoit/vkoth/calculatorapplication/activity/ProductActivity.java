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
import android.widget.Toast;

import com.stepintoit.vkoth.calculatorapplication.data.APIClient;
import com.stepintoit.vkoth.calculatorapplication.data.APIInterface;
import com.stepintoit.vkoth.calculatorapplication.adapter.ProductAdapter;
import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.data.DatabaseHelper;
import com.stepintoit.vkoth.calculatorapplication.data.MySharedPreference;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    TextView tvJsonData;
    private ArrayList<ProductModel> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        databaseHelper = DatabaseHelper.getInstance(ProductActivity.this);

        getProductData();
//        if (databaseHelper.retrieveDatabase()==null || databaseHelper.retrieveDatabase().isEmpty()) {
//            Toast.makeText(ProductActivity.this,"make api call",Toast.LENGTH_SHORT).show();
//            getProductData();
//        } else {
//            Toast.makeText(ProductActivity.this, "fetch data from db", Toast.LENGTH_SHORT).show();
//            productList = databaseHelper.fetchProduct();
//        }
    }

//    boolean checkConnection() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

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

                for (int a = 0; a < productList.size(); a++) {
                    if (productList.get(a).isFavouriteFlag()) {
                        favouriteList.add(productList.get(a));
                    }
                }
                i.putExtra("favouriteList", favouriteList);
                startActivity(i);
                return true;

                //Toast.makeText(getApplicationContext(),"favourite opened",Toast.LENGTH_SHORT).show();

            case R.id.itm_service:
                startActivity(new Intent(ProductActivity.this,MusicActivity.class));
                return true;

        }
        return (super.onOptionsItemSelected(item));
    }

    void sendProductListToRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        productAdapter = new ProductAdapter(ProductActivity.this, productList, new ProductAdapter.OnClickListener() {
            @Override
            public void setOnClickListener(ProductModel productModel) {
                Intent i = new Intent(ProductActivity.this, ProductInfoActivity.class);
                i.putExtra("ProductName",productModel);
                startActivity(i);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(ProductActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(productAdapter);
    }

    void getProductData() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Observable<ArrayList<ProductModel>> productApiObservable = apiInterface.getProductList();
        productApiObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<ProductModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<ProductModel> productModels) {
                        productList = productModels;
                        sendProductListToRecycler();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        call.enqueue(new Callback<ArrayList<ProductModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
//
//                productList = response.body();
//
////                databaseHelper.insertProduct(productList);
////                databaseHelper.insertImages(productList);
////                databaseHelper.insertTags(productList);
//
//                sendProductListToRecycler();
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
//                t.printStackTrace();
//
//            }
//        });


    }

}
