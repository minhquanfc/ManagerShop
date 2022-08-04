package com.poly.managershop.api;

import com.poly.managershop.models.Category;
import com.poly.managershop.models.Product;
import com.poly.managershop.models.Users;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("add")
    Call<Product> addProduct(@Part("tensanpham") RequestBody tensanpham,
                             @Part("giasanpham") RequestBody giasanpham,
                             @Part("mota") RequestBody mota,
                             @Part("loai") RequestBody loai,
                             @Part MultipartBody.Part anhsanpham
    );

    @GET("getallproduct")
    Call<List<Product>> getProduct();

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "delete", hasBody = true)
    Call<Product> deleteProduct(@Field("_id") String id);

    @POST("update")
    @Multipart
    Call<Product> updateProduct(@Part("_id") RequestBody id,
                                @Part("tensanpham") RequestBody tensanpham,
                                @Part("giasanpham") RequestBody giasanpham,
                                @Part("mota") RequestBody mota,
                                @Part("loai") RequestBody loai,
                                @Part MultipartBody.Part anhsanpham
    );
    //users

    @GET("getallusers")
    Call<List<Users>> getUsers();
    @FormUrlEncoded
    @POST("add")
    Call<Users> createUsers(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("phonenumber") String phonenumber,
                            @Field("address") String address
                            );

    @FormUrlEncoded
    @POST("update")
    Call<Users> updateUsers(
                            @Field("_id") String id,
                            @Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("phonenumber") String phonenumber,
                            @Field("address") String address
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "delete", hasBody = true)
    Call<Users> deleteUsers(@Field("_id") String id);

    //category
    @GET("getallcategory")
    Call<List<Category>> getCategory();

    @FormUrlEncoded
    @POST("add")
    Call<Category> addCategory(
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("update")
    Call<Category> updateCategory(
            @Field("_id") String id,
            @Field("name") String name
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "delete", hasBody = true)
    Call<Category> deleteCategory(@Field("_id") String id);
}
