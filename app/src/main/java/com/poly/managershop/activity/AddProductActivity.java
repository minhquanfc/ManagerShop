package com.poly.managershop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.poly.managershop.R;
import com.poly.managershop.api.ApiService;
import com.poly.managershop.models.Category;
import com.poly.managershop.models.Product;
import com.poly.managershop.models.RealPathUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText ed_tensanpham,ed_giasanpham,ed_mota;
    ImageView anhsanpham;
    Button button;
    Uri uri;
    Spinner spinner;
    private List<Category> listCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        toolbar = findViewById(R.id.toolbar_add_product);
        ed_tensanpham = findViewById(R.id.ed_tensanpham);
        ed_giasanpham = findViewById(R.id.ed_giasanpham);
        ed_mota = findViewById(R.id.ed_mota);
        anhsanpham = findViewById(R.id.anhsanpham);
        button = findViewById(R.id.button_add_product);
        spinner = (Spinner) findViewById(R.id.spinner);

        listCategory = new ArrayList<>();
//        listCategory.add(new Category("a","a"));
//        listCategory.add(new Category("a","b"));
//        listCategory.add(new Category("a","c"));

        getCategory();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anhsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProduct();
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
                    listCategory.addAll(response.body());

                    ArrayAdapter spinnerMSadapter = new ArrayAdapter(AddProductActivity.this, android.R.layout.simple_spinner_item, listCategory);
                    spinnerMSadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(spinnerMSadapter);

//                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        String name = listCategory.get(position).getName();
//                        Toast.makeText(AddProductActivity.this, ""+name, Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "Error connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddProduct() {
        String tensanpham = ed_tensanpham.getText().toString().trim();
        String giasanpham = ed_giasanpham.getText().toString().trim();
        String mota = ed_mota.getText().toString().trim();
        String loai = spinner.getSelectedItem().toString();
        Log.e("aaa","loai"+loai);
        String strRealPath = RealPathUtil.getRealPath(this,uri);
        Log.e("aaa","aaa"+strRealPath);
        File file = new File(strRealPath);
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"),tensanpham);
        RequestBody requestBodyPrice = RequestBody.create(MediaType.parse("multipart/form-data"),giasanpham);
        RequestBody requestBodyDes = RequestBody.create(MediaType.parse("multipart/form-data"),mota);
        RequestBody requestBodyLoai = RequestBody.create(MediaType.parse("multipart/form-data"),loai);
        RequestBody requestBodyImg = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("anhsanpham",file.getName(),requestBodyImg);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.58:3000/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Product> call = apiService.addProduct(requestBodyName,requestBodyPrice,requestBodyDes,requestBodyLoai,body);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()){
                    ed_tensanpham.setText("");
                    ed_giasanpham.setText("");
                    ed_mota.setText("");
                    Toast.makeText(AddProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddProductActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "Lỗi connect"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==2 && resultCode == RESULT_OK && data != null){
            uri = data.getData();
            anhsanpham.setImageURI(uri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}