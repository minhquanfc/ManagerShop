package com.poly.managershop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.managershop.R;

public class UsersHolder extends RecyclerView.ViewHolder {
    TextView tv_name_users,tv_email_users,tv_phonenumber,tv_address_users;
    ImageView img_edit_users,img_del_users;
    CardView click_users;
    public UsersHolder(@NonNull View itemView) {
        super(itemView);
        tv_name_users =itemView.findViewById(R.id.tv_name_users);
        tv_email_users =itemView.findViewById(R.id.tv_email_users);
        tv_phonenumber =itemView.findViewById(R.id.tv_phonenumber);
        tv_address_users =itemView.findViewById(R.id.tv_address_users);
        img_edit_users =itemView.findViewById(R.id.img_edit_users);
        img_del_users =itemView.findViewById(R.id.img_del_users);
        click_users =itemView.findViewById(R.id.click_users);
    }
}
