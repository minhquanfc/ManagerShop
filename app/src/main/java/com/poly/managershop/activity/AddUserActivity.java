package com.poly.managershop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.managershop.R;
import com.poly.managershop.adapter.UsersAdapter;
import com.poly.managershop.api.ApiService;
import com.poly.managershop.models.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText ed_name_users,ed_email_users,ed_password_users,ed_phonenumber,ed_address_users;
    Button button;
    List<Users> usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        toolbar = findViewById(R.id.toolbar_add_users);
        ed_name_users = findViewById(R.id.ed_name_users);
        ed_email_users = findViewById(R.id.ed_email_users);
        ed_password_users = findViewById(R.id.ed_password_users);
        ed_phonenumber = findViewById(R.id.ed_phonenumber);
        ed_address_users = findViewById(R.id.ed_address_users);
        button = findViewById(R.id.button_add_users);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usersList = new ArrayList<>();
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUsers();
            }
        });
    }

    private void AddUsers() {
        String name = ed_name_users.getText().toString().trim();
        String email = ed_email_users.getText().toString().trim();
        String password = ed_password_users.getText().toString().trim();
        String phonenumber = ed_phonenumber.getText().toString().trim();
        String address = ed_address_users.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.243:3000/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Users> call = apiService.createUsers(name,email,password,phonenumber,address);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddUserActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddUserActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(AddUserActivity.this, "Lỗi connect", Toast.LENGTH_SHORT).show();
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