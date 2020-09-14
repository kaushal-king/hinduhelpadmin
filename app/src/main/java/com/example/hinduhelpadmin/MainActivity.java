package com.example.hinduhelpadmin;

import android.content.Intent;
import android.os.Bundle;

import com.example.hinduhelpadmin.apiiterface.Api;
import com.example.hinduhelpadmin.apiiterface.ApiClient;
import com.example.hinduhelpadmin.apiiterface.CommanResponse;
import com.example.hinduhelpadmin.apiiterface.get_set.User;
import com.example.hinduhelpadmin.storage.sareprefrencelogin;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView t,y;
    User user;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getIntent().getExtras() != null) {
            Intent i=getIntent();
            String orderNotification = i.getStringExtra("d");
            String title=  i.getStringExtra("t");
            //  String e=  i.getStringExtra("te");


            if (orderNotification.equals("true")) {

                //  Toast.makeText(this, orderNotification+"  "+e, Toast.LENGTH_SHORT).show();
                if (title.equals("HElp Needed")){
                    //Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_nav_dash_to_nav_help);
                }
            }
        }






        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dash, R.id.nav_help, R.id.nav_helppend,
                R.id.nav_helpsolve,R.id.nav_tools)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        user= sareprefrencelogin.getInstance(getApplicationContext()).getuser();
        View hView =  navigationView.getHeaderView(0);
        t=(TextView)hView.findViewById(R.id.name);
        y=(TextView)hView.findViewById(R.id.mob);
        t.setText(user.getFname()+" "+user.getLname());
        y.setText(user.getMobileno());








        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.i("Token", token);
                saveToken(token);
            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    private void saveToken(String token) {

        Api api= ApiClient.getClient().create(Api.class);
        Call<CommanResponse> call =api.getFcm(token,user.getState(),user.getDist());
        call.enqueue(new Callback<CommanResponse>() {
            @Override
            public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                Toast.makeText(MainActivity.this, "add token", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CommanResponse> call, Throwable t) {

            }
        });
    }
}
