package com.poly.managershop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.managershop.MainActivity;
import com.poly.managershop.R;

public class SigninActivity extends AppCompatActivity {

    EditText ed_email,ed_pass;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ed_email = findViewById(R.id.ed_email_signin);
        ed_pass = findViewById(R.id.ed_pass_signin);
        button = findViewById(R.id.button_signin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                String pass = ed_pass.getText().toString().trim();
                if (email.equals("")|| pass.equals("")){
                    Toast.makeText(SigninActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                } else if (email.equalsIgnoreCase("admin@gmail.com") && pass.equalsIgnoreCase("123456")){
                    Toast.makeText(SigninActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SigninActivity.this, "Tài khoản mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}