package com.example.daandharma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerHospitalsAdapter extends RecyclerView.Adapter<RecyclerHospitalsAdapter.ViewHolder> {

    public Context context;
    public ArrayList<HospitalsModel> arrayList;

    public UserClickListener userClickListener;

    ArrayList<String> arrorgans = new ArrayList<String>();



    public interface UserClickListener{
        void selectedUser(HospitalsModel hospitalsModel);
    }

    public RecyclerHospitalsAdapter(ArrayList<HospitalsModel> hospitalsModels, Context context, UserClickListener userClickListener){

        this.context = context;
        this.arrayList = hospitalsModels;
        this.userClickListener = userClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context content = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.hospitals_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HospitalsModel hospitalsModel = arrayList.get(position);
        String hname = hospitalsModel.getHname();
        String hlocation = hospitalsModel.getHlocation();
        String horgans = hospitalsModel.getHorgan();
        holder.horgans.setText(horgans);
        holder.hname.setText(hname);
        holder.hlocation.setText(hlocation);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                userClickListener.selectedUser(hospitalsModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterList(ArrayList<HospitalsModel> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }

    public void filterList2(ArrayList<HospitalsModel> filteredList2){
        arrayList = filteredList2;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView hname, hlocation, horgans;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hname = itemView.findViewById(R.id.textViewU);
            hlocation = itemView.findViewById(R.id.minitextViewU);
            horgans = itemView.findViewById(R.id.minitextViewV);
        }
    }
}
