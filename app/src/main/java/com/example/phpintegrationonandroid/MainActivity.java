package com.example.phpintegrationonandroid;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText etFirstName,etMiddleName,etLastName,etEmail,etPassword,etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize objects;

        btnSave = findViewById(R.id.btnRegister);
        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.4.132/android-crud/includes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccountAPI accountAPI = retrofit.create(AccountAPI.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<JsonObject> call = accountAPI.register(
                        etFirstName.getText().toString(),
                        etMiddleName.getText().toString(),
                        etLastName.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etConfirmPassword.getText().toString());

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        JsonObject jsonObject = response.body();

                        if (jsonObject != null) {

                            int success = jsonObject.get("success").getAsInt();

                            String message = jsonObject.get("message").getAsString();

                            if (success == 1) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Error: Empty response.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e(TAG, "Failed to register.Try again", t);
                    }
                });


            }
        });
    }


}