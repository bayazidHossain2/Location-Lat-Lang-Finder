package com.example.daily_khoroch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daily_khoroch.Model.LocationModel;
import com.example.daily_khoroch.R;

import java.util.ArrayList;

public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.viewHolder> {

    Context context;
    ArrayList<LocationModel> list;

    public AmountAdapter(Context context,ArrayList<LocationModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_sample_desing,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        LocationModel model = list.get(position);
        holder.name.setText(model.getLocationName()+"");
        holder.lat.setText(model.getLat()+"");
        holder.lang.setText(model.getLang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name,lat,lang;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_sample_location_name);
            lat = itemView.findViewById(R.id.tv_sample_location_lat);
            lang = itemView.findViewById(R.id.tv_sample_location_lang);
        }
    }
}
