package com.example.hinduhelpadmin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hinduhelpadmin.apiiterface.Api;
import com.example.hinduhelpadmin.apiiterface.ApiClient;
import com.example.hinduhelpadmin.apiiterface.CommanResponse;
import com.example.hinduhelpadmin.apiiterface.get_set.User;
import com.example.hinduhelpadmin.apiiterface.get_set.help_get_set;
import com.example.hinduhelpadmin.storage.sareprefrencelogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class helpadapter extends RecyclerView.Adapter<helpadapter.ViewHolder>  {

String id;
    Context mcontext;
    private List<help_get_set> data;
    public helpadapter(Context mcontext,List<help_get_set> data){
        this.data=data;
        this.mcontext=mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.helplayout, null);

        final ViewHolder viewHolder=new ViewHolder(itemview);

        viewHolder.mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mo=data.get(viewHolder.getAdapterPosition()).getMobileno();
                Uri call = Uri.parse("tel:" + mo);
                Intent o = new Intent(Intent.ACTION_DIAL, call);
                mcontext.startActivity(o);
            }
        });

        viewHolder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user= sareprefrencelogin.getInstance(mcontext).getuser();
                id=data.get(viewHolder.getAdapterPosition()).getId()+"";
                Api api= ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call=api.hupone("helpone",id,"1",user.getFname()+" "+user.getLname(),user.getMobileno());
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if(response.body().getSuccess()==200)
                        {

                            data.remove(viewHolder.getAdapterPosition());
                            notifyDataSetChanged();
                            Toast.makeText(mcontext, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(mcontext, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(mcontext, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        help_get_set d=data.get(position);

        viewHolder.id.setText(d.getId()+"");
        viewHolder.name.setText(d.getFullname());
        viewHolder.date.setText(d.getDatehelp());
        viewHolder.detail.setText(d.getHdisc());
        viewHolder.hname.setText(d.getHelper());
        viewHolder.hmob.setText(d.getHmob());
        viewHolder.mob.setText(d.getMobileno());
        viewHolder.type.setText(d.getTypehelp()+"("+d.getSubhelp()+")");
        viewHolder.mode.setText(d.getHelpmode());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView id,name,date,detail,hname,hmob,mob,type,mode;
        Button b;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.helpid);
            name=(TextView) itemView.findViewById(R.id.helpname);
            date=(TextView) itemView.findViewById(R.id.helpdate);
            detail=(TextView) itemView.findViewById(R.id.helptxt);
            hname=(TextView) itemView.findViewById(R.id.helpername);
            hmob=(TextView) itemView.findViewById(R.id.helpermob);
            mob=(TextView) itemView.findViewById(R.id.help_p_mob);
            type=(TextView) itemView.findViewById(R.id.helptype);
            mode=(TextView) itemView.findViewById(R.id.helpneed);
            b=(Button) itemView.findViewById(R.id.appe_btn);


        }
    }
}
