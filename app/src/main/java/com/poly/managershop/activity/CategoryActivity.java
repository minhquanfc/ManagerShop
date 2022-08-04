package com.poly.managershop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.managershop.R;
import com.poly.managershop.adapter.CategoryAdapter;
import com.poly.managershop.adapter.ProductAdapter;
import com.poly.managershop.api.ApiService;
import com.poly.managershop.models.Category;
import com.poly.managershop.models.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    List<Category> categoryList;
    CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = findViewById(R.id.toolbar_category);
        recyclerView = findViewById(R.id.rc_view_category);
        floatingActionButton = findViewById(R.id.floatingActionButton_category);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(CategoryActivity.this,categoryList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CategoryActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getCategory();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder
                        = new AlertDialog.Builder(CategoryActivity.this);
                builder.setTitle("Add Category");
                View view
                        = getLayoutInflater()
                        .inflate(
                                R.layout.add_category_layout,
                                null);
                builder.setView(view);
                EditText ed_name_category = view.findViewById(R.id.ed_name_category);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.1.243:3000/categorys/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        ApiService apiService = retrofit.create(ApiService.class);
                        Call<Category> call = apiService.addCategory(ed_name_category.getText().toString());
                        call.enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(CategoryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    adapter.notifyDataSetChanged();
                                } else{
                                    Toast.makeText(CategoryActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {
                                Toast.makeText(CategoryActivity.this, "Lỗi connect", Toast.LENGTH_SHORT).show();
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
    }

    private void getCategory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.58:3000/categorys/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Category>> call = apiService.getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful())
                {
                    categoryList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Error connect", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}