package com.poly.managershop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.managershop.R;

public class CategoryHolder extends RecyclerView.ViewHolder {
    TextView tv_name_category;
    ImageView img_edit_category,img_del_category;
    CardView click_category;
    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        tv_name_category =itemView.findViewById(R.id.tv_name_category);
        img_edit_category =itemView.findViewById(R.id.img_edit_category);
        img_del_category =itemView.findViewById(R.id.img_del_category);
        click_category =itemView.findViewById(R.id.click_category);
    }
}
