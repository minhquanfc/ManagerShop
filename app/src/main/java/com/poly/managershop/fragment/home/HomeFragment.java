package com.poly.managershop.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.poly.managershop.R;
import com.poly.managershop.activity.AddProductActivity;
import com.poly.managershop.activity.CategoryActivity;
import com.poly.managershop.activity.ProductActivity;
import com.poly.managershop.activity.UsersActivity;

public class HomeFragment extends Fragment {

    CardView cardView_addproduct,cartview_listproduct,cardview_users, cardview_category;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardView_addproduct=view.findViewById(R.id.cardview_addproduct);
        cartview_listproduct=view.findViewById(R.id.cardview_product);
        cardview_users=view.findViewById(R.id.cardview_users);
        cardview_category=view.findViewById(R.id.cardview_category);


        cardView_addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddProductActivity.class);
                startActivity(intent);
            }
        });

        cartview_listproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                startActivity(intent);
            }
        });
        cardview_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsersActivity.class);
                startActivity(intent);
            }
        });
        cardview_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}