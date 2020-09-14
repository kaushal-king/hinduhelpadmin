package com.example.hinduhelpadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.hinduhelpadmin.apiiterface.Api;
import com.example.hinduhelpadmin.apiiterface.ApiClient;
import com.example.hinduhelpadmin.apiiterface.responce.loginresponce;
import com.example.hinduhelpadmin.storage.sareprefrencelogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginactivity extends AppCompatActivity {
    TextView re;
    EditText no, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);


        no = (EditText) findViewById(R.id.mono);
        pass = (EditText) findViewById(R.id.password);


    }
    @Override
    protected void onStart() {
        super.onStart();
        if(sareprefrencelogin.getInstance(this).islogin())
        {
            Intent i = new Intent(Loginactivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

    }





    public void login(View view) {

        String n=no.getText().toString();
        String p=pass.getText().toString();
        Api api = ApiClient.getClient().create(Api.class);
        Call<loginresponce> call=api.login("loginmember",n,p);
        call.enqueue(new Callback<loginresponce>() {
            @Override
            public void onResponse(Call<loginresponce> call, Response<loginresponce> response) {
                if (response.body().getSuccess()==405) {
                    loginresponce loginresponce=response.body();
                    sareprefrencelogin.getInstance(Loginactivity.this).saveuser(loginresponce.getUser());
                    Intent i = new Intent(Loginactivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(Loginactivity.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Loginactivity.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginresponce> call, Throwable t) {
                Toast.makeText(Loginactivity.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
