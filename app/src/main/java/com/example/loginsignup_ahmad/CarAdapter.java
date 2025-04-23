package com.example.loginsignup_ahmad;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
        Context context;
        ArrayList<Car> restList;
        private FireBaseServices fbs;
        private OnItemClickListener itemClickListener;


        public CarAdapter(Context context, ArrayList<Car> carsList) {
            this.context = context;
            this.restList = carsList;
        }

        @NonNull
        @Override
        public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
            View v= LayoutInflater.from(context).inflate(R.layout.car_item,parent,false);
            return  new CarAdapter.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
            Car car = restList.get(position);
            holder.carName.setText(car.getNameCar());
            holder.Price.setText(car.getPrice() + " ₪");
            holder.Year.setText(car.getYear());
            holder.location.setText(car.getHorse_power() + "Hp");
            holder.GearShift.setText(car.getGear_shifting_model());
            holder.kilometre.setText(car.getKilometre() + "Km");
            holder.carName.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            });
            if (car.getPhoto() == null || car.getPhoto().isEmpty()) {
                Picasso.get().load(R.drawable.ic_launcher_background).into(holder.ivCar);
            } else {
                Picasso.get().load(car.getPhoto()).into(holder.ivCar);
            }
            holder.ivFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getActivity(), "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show();
                    Bundle args = new Bundle();
                    args.putParcelable("car", (Parcelable) car); // or use Parcelable for better performance
                    CarDetailsFragment cd = new CarDetailsFragment();
                    cd.setArguments(args);
                    FragmentTransaction ft = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main, cd);
                    ft.commit();
                }
            });
        }

        @Override
        public int getItemCount(){
            return restList.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder{
            TextView kilometre,GearShift,carName,location,Year,Price;
            ImageView imageView,ivCar, ivFavourite;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                carName=itemView.findViewById(R.id.tvNameCar_carListFragment);
                Price=itemView.findViewById(R.id.tvPrice_carListFragment);
                Year=itemView.findViewById(R.id.tvYear_carListFragment);
                location=itemView.findViewById(R.id.tvlocation_carListFragment);
                GearShift=itemView.findViewById(R.id.tvGearShift_carListFragment);
                kilometre=itemView.findViewById(R.id.tvKelometer_carListFragment);
                ivCar = itemView.findViewById(R.id.ivCarPhotoItem);
                ivFavourite = itemView.findViewById(R.id.ivFavoriteIcon);
            }
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.itemClickListener = listener;
        }
    }
