package com.example.hinduhelpadmin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hinduhelpadmin.apiiterface.get_set.help_get_set;

import java.util.List;

public class helpcmpadapter extends RecyclerView.Adapter<helpcmpadapter.ViewHolder> {

    Context mcontext;
    private List<help_get_set> data;
    public helpcmpadapter(Context mcontext,List<help_get_set> data){
        this.data=data;
        this.mcontext=mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmplayout, null);

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
            id=(TextView) itemView.findViewById(R.id.helpcmpid);
            name=(TextView) itemView.findViewById(R.id.helpcmpname);
            date=(TextView) itemView.findViewById(R.id.helpcmpdate);
            detail=(TextView) itemView.findViewById(R.id.helpcmptxt);
            hname=(TextView) itemView.findViewById(R.id.helpercmpname);
            hmob=(TextView) itemView.findViewById(R.id.helpercmpmob);
            type=(TextView) itemView.findViewById(R.id.helptypecmp);
            mode=(TextView) itemView.findViewById(R.id.helpneedcmp);
            mob=(TextView) itemView.findViewById(R.id.helpmoboo);


        }
    }
}
