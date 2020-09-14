package com.example.hinduhelpadmin.ui.solve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hinduhelpadmin.R;
import com.example.hinduhelpadmin.apiiterface.Api;
import com.example.hinduhelpadmin.apiiterface.ApiClient;
import com.example.hinduhelpadmin.apiiterface.get_set.User;
import com.example.hinduhelpadmin.apiiterface.get_set.help_get_set;
import com.example.hinduhelpadmin.apiiterface.responce.helpresponce;
import com.example.hinduhelpadmin.helpcmpadapter;
import com.example.hinduhelpadmin.helppendadapter;
import com.example.hinduhelpadmin.storage.sareprefrencelogin;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class solveFragment extends Fragment {
    RecyclerView recyclerView;
    List<help_get_set> li;
    helpcmpadapter ada;
    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_solve, container, false);
        recyclerView=(RecyclerView) root.findViewById(R.id.helpsolvedrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadhelpcmp();
        return root;
    }



    public void loadhelpcmp()
    {
        User user;
        user= sareprefrencelogin.getInstance(getContext()).getuser();
        Api api= ApiClient.getClient().create(Api.class);
        Call<helpresponce> call=api.hcmpp("helpdetail",user.getState(),user.getDist());
        call.enqueue(new Callback<helpresponce>() {
            @Override
            public void onResponse(Call<helpresponce> call, Response<helpresponce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ada=new helpcmpadapter(getContext(),li);
                    recyclerView.setAdapter(ada);
                }
                else {
                    Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<helpresponce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

}