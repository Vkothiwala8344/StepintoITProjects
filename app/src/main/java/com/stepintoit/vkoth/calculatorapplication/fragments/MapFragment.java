package com.stepintoit.vkoth.calculatorapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;


public class MapFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";

    private ProductModel product;



    public MapFragment() {

    }


    public static MapFragment newInstance(ProductModel product) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (ProductModel)getArguments().getSerializable(ARG_PRODUCT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

}
