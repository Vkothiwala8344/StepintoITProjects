package com.stepintoit.vkoth.calculatorapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<ProductModel> productModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, tags, location, imageLinks;
        public ImageView pImage;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tv_product_name);
            location = (TextView) view.findViewById(R.id.tv_product_location);
            tags = (TextView) view.findViewById(R.id.tv_product_tags);
            imageLinks = (TextView) view.findViewById(R.id.tv_product_images);
            pImage = (ImageView) view.findViewById(R.id.iv_productImage);
        }
    }

    public ProductAdapter(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProductModel productModel = productModelList.get(position);
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


    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }


}
