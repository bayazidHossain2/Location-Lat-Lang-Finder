package com.example.daily_khoroch.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daily_khoroch.Model.Khoroch_Model;
import com.example.daily_khoroch.R;

import java.util.ArrayList;

public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.viewHolder> {

    Context context;
    ArrayList<Khoroch_Model> list;

    public AmountAdapter(Context context,ArrayList<Khoroch_Model> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_khoroch,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Khoroch_Model model = list.get(position);
        holder.amount.setText(model.getAmount()+"");
        holder.desc.setText(model.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView amount,desc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.tv_sample_amount);
            desc = itemView.findViewById(R.id.tv_sample_short_desc);
        }
    }
}
