package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>  {

    private List<ProductModel> productModelList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, tags, location, imageLinks;
        public ImageView pImage,fImageview;



        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tv_product_name);
            location = (TextView) view.findViewById(R.id.tv_product_location);
            tags = (TextView) view.findViewById(R.id.tv_product_tags);
            imageLinks = (TextView) view.findViewById(R.id.tv_product_images);
            pImage = (ImageView) view.findViewById(R.id.iv_productImage);
            fImageview = (ImageView)view.findViewById(R.id.iv_favourite);
            fImageview.setVisibility(View.INVISIBLE);


        }

    }

    public FavouriteAdapter(Context context,List<ProductModel> productModelList) {
        this.productModelList = productModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteAdapter.MyViewHolder holder, final int position) {


        final ProductModel productModel = productModelList.get(position);
        holder.name.setText(productModel.getProductName());

        holder.location.setText("Latitude : " + Double.toString(productModel.getWarehouseLocationModel().getLatitude()) + ", Longitude : " +
                Double.toString(productModel.getWarehouseLocationModel().getLongitude()));
        holder.tags.setText("Tags : " + productModel.getTags().get(0) + "," + productModel.getTags().get(1));
        holder.imageLinks.setText("Image links : ");
        int i = 0;
        do {
            holder.imageLinks.append(productModel.getProductImage().get(i) + "\n");
            i++;

        } while (i < productModel.getProductImage().size() - 1);
        holder.imageLinks.append(productModel.getProductImage().get(i));
        holder.pImage.setImageResource(R.drawable.iphone);

        if(productModel.isFavouriteFlag())
        {
            holder.fImageview.setImageResource(R.drawable.ic_heart_red);
            productModel.setFavouriteFlag(true);
        }

    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

}
