package com.stepintoit.vkoth.calculatorapplication.adapter;

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

import com.stepintoit.vkoth.calculatorapplication.R;
import com.stepintoit.vkoth.calculatorapplication.activity.ProductInfoActivity;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<ProductModel> productModelList;
    Context context;
    OnClickListener onClickListener;

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


        }

    }

    public ProductAdapter(Context context,List<ProductModel> productModelList,OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
        holder.name.setText(productModel.getName());

        holder.location.setText("Latitude : " + Double.toString(productModel.getWarehouseLocation().getLatitude()) + ", Longitude : " +
                Double.toString(productModel.getWarehouseLocation().getLongitude()));
        holder.tags.setText("Tags : " + productModel.getTags().get(0) + "," + productModel.getTags().get(1));
        holder.imageLinks.setText("Image links : ");
        int i = 0;
        do {
            holder.imageLinks.append(productModel.getImages().get(i) + "\n");
            i++;

        } while (i < productModel.getImages().size() - 1);
        holder.imageLinks.append(productModel.getImages().get(i));
        holder.pImage.setImageResource(R.drawable.iphone);

//        if(productModel.isFavouriteFlag())
//        {
//            holder.fImageview.setImageResource(R.drawable.ic_heart_red);
//            productModel.setFavouriteFlag(true);
//        }

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

                if(onClickListener!= null)
                {
                    onClickListener.setOnClickListener(productModel);
                }

                }
        });


        holder.fImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!productModel.isFavouriteFlag())
                {
                    holder.fImageview.setImageResource(R.drawable.ic_heart_red);
                    productModel.setFavouriteFlag(true);
                   // favP.add(productModel.getProductName());
                  //  Log.d("ProductAdapter","productName"+favP.get(position));

                }
                else
                {
                    holder.fImageview.setImageResource(R.drawable.ic_heart_white);
                    productModel.setFavouriteFlag(false);
                  //  favP.remove(position);

                }


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

    public interface OnClickListener
    {
       void setOnClickListener(ProductModel productModel);
    }


}
