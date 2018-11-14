package com.stepintoit.vkoth.calculatorapplication;

import android.app.ListActivity;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<ProductModel> productModelList;
    Context context;

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

    public ProductAdapter(Context context,List<ProductModel> productModelList) {
        this.productModelList = productModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               //Toast.makeText(context,"bavj",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Delete");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        delete(position);
                        Toast.makeText(context,holder.name.getText()+" deleted",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ProductInfoActivity.class);
                i.putExtra("ProductName",productModel.getProductName().toString());

                //pas simage bundle
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("ProductImageLinks",productModel.getProductImage());
                bundle.putStringArrayList("ProductTags",productModel.getTags());
                i.putExtras(bundle);


                // i.putExtra("ProductTag",productModel.getTags());
                i.putExtra("ProductLocationLatitude",productModel.getWarehouseLocationModel().getLatitude());
                i.putExtra("ProductLocationLongitude",productModel.getWarehouseLocationModel().getLongitude());
                context.startActivity(i);
                }
        });

    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public void delete(int position) { //removes the row
        productModelList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


}
