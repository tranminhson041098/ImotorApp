package com.linearlayout.imotorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.linearlayout.imotorapp.APIService.ImotorService;
import com.linearlayout.imotorapp.Model.Login;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtPhoneNumber;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Xuly();

    }

    void init() {
        btnLogin = findViewById(R.id.btn_login);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
    }

    void Xuly() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                getdata();


                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("LoginProcessing");
                progressDialog.setMessage("Loading........");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (edtPhoneNumber.getText().toString().equals(phoneNumber)) {

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            setPhoneNumber();

                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();

                            Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại số điện thoại", Toast.LENGTH_SHORT).show();
                        }
                    }
                },5000);




            }
        });

    }

    //Hàm lưu vào sharepreferences
    void setPhoneNumber() {
        SharedPreferences sharedPreferences = getSharedPreferences("ImotorSource", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phoneNumber", phoneNumber);
        editor.apply();


    }

    public void getdata() {


        GetLoginInfo getLoginInfo = new GetLoginInfo("0387490078", "hjgjhg", "android");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://45.118.144.19:1904/api/")
                .build();
        retrofit.create(ImotorService.class).getInfoLogin(getLoginInfo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String strJson = response.body().string();
                    Gson gson = new Gson();
                    Login login = gson.fromJson(strJson, Login.class);
                    phoneNumber = login.getResult().getPhone().toString();
                    Log.d("Show phone Number", "" + phoneNumber.toString());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Không thể lấy được dữ liệu", Toast.LENGTH_LONG).show();

            }
        });

    }

    class GetLoginInfo {
        String phoneNumber, DeviceID, Os;

        public GetLoginInfo(String phoneNumber, String deviceID, String os) {
            this.phoneNumber = phoneNumber;
            DeviceID = deviceID;
            Os = os;
        }
    }
}
