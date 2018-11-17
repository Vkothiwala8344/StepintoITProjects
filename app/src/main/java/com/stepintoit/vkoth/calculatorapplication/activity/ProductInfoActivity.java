package com.stepintoit.vkoth.calculatorapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.stepintoit.vkoth.calculatorapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductInfoActivity extends AppCompatActivity {

    TextView tvProductName,tvImageLinks,tvTags,tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        tvProductName = (TextView)findViewById(R.id.tv_product_name);
        tvImageLinks = (TextView)findViewById(R.id.tv_image_links);
        tvTags = (TextView)findViewById(R.id.tv_tags);
        tvLocation = (TextView)findViewById(R.id.tv_location);


        Intent intentData = getIntent();
        String productName = intentData.getExtras().getString("ProductName");
        tvProductName.append(productName);

        Bundle bundle = getIntent().getExtras();
        //get image links from arraylist
        ArrayList<String> productImageLinks = bundle.getStringArrayList("ProductImageLinks");
        int i=0;
        do{
            tvImageLinks.append(productImageLinks.get(i)+"\n");
            i++;
        }while (i<productImageLinks.size()-1);
        tvImageLinks.append(productImageLinks.get(i));

        //get tags from arraylist
        ArrayList<String> productTags = bundle.getStringArrayList("ProductTags");
        Log.d("ProductINfo","tags:"+productTags.size());
        tvTags.append(productTags.get(0)+", "+productTags.get(1));

        double latitude = bundle.getDouble("ProductLocationLatitude");
        double longitude = bundle.getDouble("ProductLocationLongitude");
        //get lat and longitude

        tvLocation.append("Latitude = "+Double.toString(latitude)+", Longitude = "+Double.toString(longitude));

    }
}
