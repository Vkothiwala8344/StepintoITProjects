package com.stepintoit.vkoth.calculatorapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stepintoit.vkoth.calculatorapplication.adapter.FavouriteAdapter;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;
import com.stepintoit.vkoth.calculatorapplication.R;

import java.util.ArrayList;

public class FavouriteProductActivity extends AppCompatActivity {

    private ArrayList<ProductModel> favouriteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavouriteAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_favourite);


        ArrayList<ProductModel> favouriteList;
        favouriteList = (ArrayList<ProductModel>)getIntent().getSerializableExtra("favouriteList");
//        int count = getIntent().getExtras().getInt("count");
//        for (int i = 0; i < count; i++) {
//            ProductModel productModel = (ProductModel) getIntent().getSerializableExtra("Fav" + i);
//            favouriteList.add(productModel);
//            //  Log.d("FavActivity", productModel.getProductName());
//              }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        favouriteAdapter = new FavouriteAdapter(FavouriteProductActivity.this,favouriteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(FavouriteProductActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(favouriteAdapter);

    }
}
