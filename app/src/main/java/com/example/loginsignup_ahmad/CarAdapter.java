package com.example.loginsignup_ahmad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
        Context context;
        ArrayList<Car> restList;
        private FireBaseServices fbs;

        public CarAdapter(Context context, ArrayList<Car> restList) {
            this.context = context;
            this.restList = restList;
            this.fbs = FireBaseServices.getInstance();
        }

        @NonNull
        @Override
        public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View v= LayoutInflater.from(context).inflate(R.layout.car_item,parent,false);
            return  new CarAdapter.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder holder, int position) {
            Car rest = restList.get(position);
            holder.tvName.setText(rest.getName());
            holder.tvPhone.setText(rest.getPhone());
        }

        @Override
        public int getItemCount(){
            return restList.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvName, tvPhone;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName=itemView.findViewById(R.id.tvNameCarItem);
                tvPhone=itemView.findViewById(R.id.tvPhoneCarItem);

            }
        }
    }


