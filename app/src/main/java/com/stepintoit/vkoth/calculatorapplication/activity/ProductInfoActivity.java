package com.stepintoit.vkoth.calculatorapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.fragments.DaetailsFragment;
import com.stepintoit.vkoth.calculatorapplication.fragments.MapFragment;
import com.stepintoit.vkoth.calculatorapplication.fragments.WebFragment;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductInfoActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerSlidingTabStrip pagerTabStrip;
    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        pagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_header);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        productModel = (ProductModel)getIntent().getSerializableExtra("ProductName");


        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());



        viewPager.setAdapter(myPagerAdapter);

        pagerTabStrip.setAllCaps(true);
        pagerTabStrip.setShouldExpand(true);
        pagerTabStrip.setViewPager(viewPager);




    }

    public class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0)
            {
                DaetailsFragment daetailsFragment = DaetailsFragment.newInstance(productModel);
                return  daetailsFragment;
            }
            else if (position ==1)
            {
                MapFragment mapFragment = MapFragment.newInstance(productModel);
                return mapFragment;
            }
            else if (position ==2)
                {
                   WebFragment webFragment = WebFragment.newInstance(productModel);
                   return webFragment;
                }
            else
            {
                return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(position ==0)
            {
                return "Details";
            }
            else if(position == 1)
            {
                return "Maps";
            }
            else if(position == 2)
                {
                return "Web Page";
                }
            else {
                return null;
            }
        }


    }
}
