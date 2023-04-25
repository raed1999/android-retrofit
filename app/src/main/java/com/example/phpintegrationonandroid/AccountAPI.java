package com.example.phpintegrationonandroid;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountAPI {

    @FormUrlEncoded   @POST("create.inc.php")
    Call<JsonObject> register(
            @Field("firstName") String name,
            @Field("middleName") String middleName,
            @Field("lastName") String lastName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("confirmPassword") String confirmPassword
    );





}
