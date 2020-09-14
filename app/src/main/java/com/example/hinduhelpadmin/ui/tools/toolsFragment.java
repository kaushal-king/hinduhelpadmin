package com.example.hinduhelpadmin.ui.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hinduhelpadmin.Loginactivity;
import com.example.hinduhelpadmin.MainActivity;
import com.example.hinduhelpadmin.R;
import com.example.hinduhelpadmin.apiiterface.Api;
import com.example.hinduhelpadmin.apiiterface.ApiClient;
import com.example.hinduhelpadmin.apiiterface.get_set.User;
import com.example.hinduhelpadmin.apiiterface.responce.loginresponce;
import com.example.hinduhelpadmin.apiiterface.responce.prof_responce;
import com.example.hinduhelpadmin.storage.sareprefrencelogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class toolsFragment extends Fragment {
    TextView name,mob;
    TextView log,personal,change;
    AlertDialog.Builder builder;
    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        log=(TextView) root.findViewById(R.id.exit_pro);
        name=(TextView) root.findViewById(R.id.user_name);
        mob=(TextView) root.findViewById(R.id.user_mob);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sareprefrencelogin.getInstance(getContext()).clear();
                Intent i=new Intent(getContext(), Loginactivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });



        root.findViewById(R.id.personal_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder= new AlertDialog.Builder(getContext());
                LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.fragment_personaldetails,null);
                builder.setView(v);
                builder.setCancelable(true);
                final AlertDialog alert=builder.create();
                final User user= sareprefrencelogin.getInstance(getContext()).getuser();
                String fname=user.getFname();
                String lname=user.getLname();
                String email=user.getEmail()+" ";
                String mob=user.getMobileno();

                final EditText efname,elname,eemail,emobno;
                Button save;
                efname=v.findViewById(R.id.personal_fname);
                elname=v.findViewById(R.id.personal_lname);
                eemail=v.findViewById(R.id.personal_email);
                emobno=v.findViewById(R.id.personal_mobno);
                save=v.findViewById(R.id.personal_save);

                efname.setText(fname);
                elname.setText(lname);
                eemail.setText(email);
                emobno.setText(mob);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Api api = ApiClient.getClient().create(Api.class);
                        Call<loginresponce> call=api.personalupdate("profilepersonal",
                                efname.getText()+"",
                                elname.getText()+"",
                                emobno.getText()+"",
                                eemail.getText()+"",
                                user.getId()+"");
                        call.enqueue(new Callback<loginresponce>() {
                            @Override
                            public void onResponse(Call<loginresponce> call, Response<loginresponce> response) {
                                if (response.body().getSuccess()==200) {
                                    loginresponce loginresponce=response.body();
                                    sareprefrencelogin.getInstance(getContext()).saveuser(loginresponce.getUser());
                                    Intent i = new Intent(getContext(), MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<loginresponce> call, Throwable t) {
                                Toast.makeText(getContext(), "Mobile no Already exist", Toast.LENGTH_SHORT).show();
                            }
                        });


                        alert.dismiss();
                    }
                });

                alert.show();
            }
        });








        root.findViewById(R.id.changepass_pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder= new AlertDialog.Builder(getContext());
                LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.fragment_forgetpassword,null);
                builder.setView(v);
                builder.setCancelable(true);
                final AlertDialog alert=builder.create();

                final User user= sareprefrencelogin.getInstance(getContext()).getuser();
//                String fname=user.getFname();
//                String lname=user.getLname();
//                String email=user.getEmail()+" ";
//                String mob=user.getMobno();

                final EditText ecpass,enewpass,ecnewpass;
                Button save;
                ecpass=v.findViewById(R.id.chngopass);
                enewpass=v.findViewById(R.id.chngnpass);
                ecnewpass=v.findViewById(R.id.chngchecknpass);

                save=v.findViewById(R.id.savebtn_change);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (enewpass.getText().toString().equals(ecnewpass.getText().toString())) {
                            Api api = ApiClient.getClient().create(Api.class);

                            Call<prof_responce> call = api.profpass("profilepersonal", user.getMobileno() + "",
                                    ecpass.getText()+"",
                                    enewpass.getText()+"");
                            call.enqueue(new Callback<prof_responce>() {
                                @Override
                                public void onResponse(Call<prof_responce> call, Response<prof_responce> response) {

                                    if (response.body().getSuccess()==200) {
                                        Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                        alert.dismiss();
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<prof_responce> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            ecnewpass.setError("Password Doesn`t Match");
                        }
                    }
                });

                //alert.dismiss();

                alert.show();

            }
        });
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user= sareprefrencelogin.getInstance(getContext()).getuser();
        String s=user.getFname()+" "+user.getLname();
        name.setText(s);
        mob.setText(user.getMobileno());
    }
}