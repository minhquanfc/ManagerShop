package com.poly.managershop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.managershop.R;

public class ProductHolder extends RecyclerView.ViewHolder {
    TextView tv_tensanpham,tv_giasanpham,tv_mota;
    ImageView anhsanpham,img_edit,img_del;
    CardView click;
    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        tv_tensanpham =itemView.findViewById(R.id.tv_tensanpham);
        tv_giasanpham =itemView.findViewById(R.id.tv_giasanpham);
        tv_mota =itemView.findViewById(R.id.tv_mota);
        anhsanpham =itemView.findViewById(R.id.anhsanpham);
        img_edit =itemView.findViewById(R.id.img_edit);
        img_del =itemView.findViewById(R.id.img_del);
        click =itemView.findViewById(R.id.click_product);

    }
}
