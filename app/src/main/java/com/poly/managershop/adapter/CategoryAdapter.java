package com.poly.managershop.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.managershop.R;
import com.poly.managershop.activity.CategoryActivity;
import com.poly.managershop.api.ApiService;
import com.poly.managershop.models.Category;
import com.poly.managershop.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tv_name_category.setText(category.getName());

        holder.img_edit_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder
                        = new androidx.appcompat.app.AlertDialog.Builder(context);
                builder.setTitle("Add Category");
                LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(R.layout.update_category_layout, null);
                builder.setView(v);
                EditText ed_name_category_update = v.findViewById(R.id.ed_name_category_update);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = category.get_id();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.10.58:3000/categorys/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        ApiService apiService = retrofit.create(ApiService.class);
                        Call<Category> call = apiService.updateCategory(id,ed_name_category_update.getText().toString());
                        call.enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                } else{
                                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {
                                Toast.makeText(context, "Lỗi connect", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        holder.img_del_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn có muốn xóa không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = category.get_id();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.10.58:3000/categorys/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        ApiService apiService = retrofit.create(ApiService.class);
                        Call<Category> call = apiService.deleteCategory(id);
                        call.enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                } else{
                                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                                }
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {
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
        return categoryList.size();
    }
}
