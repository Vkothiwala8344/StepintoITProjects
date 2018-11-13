package com.stepintoit.vkoth.calculatorapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public final View itemView;
    public final TextView name;

    public ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        name = itemView.findViewById(R.id.tv_product_name);

    }
}
