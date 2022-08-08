package com.poly.managershop.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.managershop.R;
import com.poly.managershop.activity.AddUserActivity;
import com.poly.managershop.activity.UpdateUsersActivity;
import com.poly.managershop.api.ApiService;
import com.poly.managershop.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersAdapter extends RecyclerView.Adapter<UsersHolder> {
    Context context;
    List<Users> usersList;

    public UsersAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemusers,parent,false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, @SuppressLint("RecyclerView") int position) {
        Users users = usersList.get(position);
        holder.tv_name_users.setText("Tên: "+users.getName());
        holder.tv_email_users.setText("Email: "+users.getEmail());
        holder.tv_phonenumber.setText("Số điện thoại: "+users.getPhonenumber());
        holder.tv_address_users.setText("Địa chỉ: "+users.getAddress());

        holder.img_edit_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateUsersActivity.class);
                intent.putExtra("id",usersList.get(position).get_id());
                context.startActivity(intent);
            }
        });
        holder.img_del_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có muốn xóa không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = users.get_id();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.10.58:3000/users/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        ApiService apiService = retrofit.create(ApiService.class);
                        Call<Users> call = apiService.deleteUsers(id);
                        call.enqueue(new Callback<Users>() {
                            @Override
                            public void onResponse(Call<Users> call, Response<Users> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Users> call, Throwable t) {
                                Toast.makeText(context, "Lỗi connect", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
