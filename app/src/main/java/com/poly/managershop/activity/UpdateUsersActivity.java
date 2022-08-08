package com.poly.managershop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.managershop.R;
import com.poly.managershop.api.ApiService;
import com.poly.managershop.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateUsersActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText ed_name_users_update,ed_email_users_update,ed_password_users_update,ed_phonenumber_update,ed_address_users_update;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_users);
        toolbar = findViewById(R.id.toolbar_update_users);
        ed_name_users_update = findViewById(R.id.ed_name_users_update);
        ed_email_users_update = findViewById(R.id.ed_email_users_update);
        ed_password_users_update = findViewById(R.id.ed_password_users_update);
        ed_phonenumber_update = findViewById(R.id.ed_phonenumber_update);
        ed_address_users_update = findViewById(R.id.ed_address_users_update);
        button = findViewById(R.id.button_update_users);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUsers();
            }
        });
    }

    private void updateUsers() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = ed_name_users_update.getText().toString().trim();
        String email = ed_email_users_update.getText().toString().trim();
        String password = ed_password_users_update.getText().toString().trim();
        String phonenumber = ed_phonenumber_update.getText().toString().trim();
        String address = ed_address_users_update.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.58:3000/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Users> call = apiService.updateUsers(id,name,email,password,phonenumber,address);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UpdateUsersActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateUsersActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(UpdateUsersActivity.this, "Lỗi connect", Toast.LENGTH_SHORT).show();
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