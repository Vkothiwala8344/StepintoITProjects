package com.stepintoit.vkoth.calculatorapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;

import java.util.ArrayList;

public class DaetailsFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";
    TextView tvProductName, tvImageLinks, tvTags, tvLocation,tvTelephone;
    ImageView imageView;


    private ProductModel product;

    public DaetailsFragment() {

    }


    public static DaetailsFragment newInstance(ProductModel product) {
        DaetailsFragment fragment = new DaetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (ProductModel) getArguments().getSerializable(ARG_PRODUCT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daetails, container, false);
        // Inflate the layout for this fragment
        tvProductName = (TextView) view.findViewById(R.id.tv_product_name);
        tvImageLinks = (TextView) view.findViewById(R.id.tv_image_links);
        tvTags = (TextView) view.findViewById(R.id.tv_tags);
        tvLocation = (TextView) view.findViewById(R.id.tv_location);
        imageView = (ImageView)view.findViewById(R.id.iv_phone);
        tvTelephone = (TextView)view.findViewById(R.id.tv_telephone);

        tvProductName.append(product.getName());


        //get image links from arraylist
        ArrayList<String> productImageLinks = product.getImages();
        int i=0;
        do{
            tvImageLinks.append(productImageLinks.get(i)+"\n");
            i++;
        }while (i<productImageLinks.size()-1);
        tvImageLinks.append(productImageLinks.get(i));

        Glide.with(this).load(product.getImages().get(0)).into(imageView);

        //get tags from arraylist
        ArrayList<String> productTags = product.getTags();
        Log.d("ProductINfo","tags:"+productTags.size());
        tvTags.append(productTags.get(0)+", "+productTags.get(1));

        double latitude = product.getWarehouseLocation().getLatitude();
        double longitude =product.getWarehouseLocation().getLongitude();
        //get lat and longitude

        tvLocation.append("Latitude = "+Double.toString(latitude)+", Longitude = "+Double.toString(longitude));
        tvTelephone.append(product.getPhone());
        tvTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
