package com.example.client.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.CarWashing_Standart;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ActivityCarWashing_Standart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child CarWashing
    DatabaseReference carWashingRef = myDbReference.child("CarWashing");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tire_fitting_view);

        recyclerView = findViewById(R.id.CarWashing_Standart_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdCarWashingStandart;
        public TextView txtTitleCarWashingStandart;
        public TextView txtPriceCarWashingStandart_sedan;
        public TextView txtPriceCarWashingStandart_business;
        public TextView txtPriceCarWashingStandart_premium;
        public TextView txtPriceCarWashingStandart_SUV;
        public TextView txtPriceCarWashingStandart_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdCarWashingStandart = itemView.findViewById(R.id.id_title);
            txtTitleCarWashingStandart = itemView.findViewById(R.id.list_title);
            txtPriceCarWashingStandart_sedan = itemView.findViewById(R.id.priceCw_sedan);
            txtPriceCarWashingStandart_business = itemView.findViewById(R.id.priceCw_business);
            txtPriceCarWashingStandart_premium = itemView.findViewById(R.id.priceCw_premium);
            txtPriceCarWashingStandart_SUV = itemView.findViewById(R.id.priceCw_SUV);
            txtPriceCarWashingStandart_BigSUV = itemView.findViewById(R.id.priceCw_BigSUV);

        }

        public void setTxtIdCarWashingStandart(String string) {
            txtIdCarWashingStandart.setText(string);
        }

        public void setTxtTitleCarWashingStandart(String string) {
            txtTitleCarWashingStandart.setText(string);
        }

        public void setTxtCarWashingStandart_sedan(String string) {
            txtPriceCarWashingStandart_sedan.setText(string);
        }

        public void setTxtPriceCarWashingStandart_business(String string) {
            txtPriceCarWashingStandart_business.setText(string);
        }

        public void setTxtPriceCarWashingStandart_premium(String string) {
            txtPriceCarWashingStandart_premium.setText(string);
        }

        public void setTxtPriceCarWashingStandart_SUV(String string) {
            txtPriceCarWashingStandart_SUV.setText(string);
        }

        public void setTxtPriceCarWashingStandart_BigSUV(String string) {
            txtPriceCarWashingStandart_BigSUV.setText(string);
        }
    }

    private void fetch() {
        Query query = carWashingRef;

        FirebaseRecyclerOptions<CarWashing_Standart> options =
                new FirebaseRecyclerOptions.Builder<CarWashing_Standart>()
                        .setQuery(query, new SnapshotParser<CarWashing_Standart>() {
                            @NonNull
                            @Override
                            public CarWashing_Standart parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new CarWashing_Standart(snapshot.child("idCW_Standart").getValue().toString(),
                                        snapshot.child("titleCW_Standart").getValue().toString(),
                                        snapshot.child("priceCW_Standart_sedan").getValue().toString(),
                                        snapshot.child("priceCW_Standart_business").getValue().toString(),
                                        snapshot.child("priceCW_Standart_premium").getValue().toString(),
                                        snapshot.child("priceCW_Standart_SUV").getValue().toString(),
                                        snapshot.child("priceCW_Standart_BigSUV").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<CarWashing_Standart, ActivityCarWashing_Standart.ViewHolder>(options) {

            @Override
            public ActivityCarWashing_Standart.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.car_washing_standart_list, parent, false);

                return new ActivityCarWashing_Standart.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ActivityCarWashing_Standart.ViewHolder holder, final int position, CarWashing_Standart carWashing_standart) {
                holder.setTxtIdCarWashingStandart(carWashing_standart.getIdCw_Standart());
                holder.setTxtTitleCarWashingStandart(carWashing_standart.getTitleCw_Standart());
                holder.setTxtCarWashingStandart_sedan(carWashing_standart.getPriceCw_Standart_sedan());
                holder.setTxtPriceCarWashingStandart_business(carWashing_standart.getPriceCw_Standart_business());
                holder.setTxtPriceCarWashingStandart_premium(carWashing_standart.getPriceCw_Standart_premium());
                holder.setTxtPriceCarWashingStandart_SUV(carWashing_standart.getPriceCw_Standart_SUV());
                holder.setTxtPriceCarWashingStandart_BigSUV(carWashing_standart.getPriceCw_Standart_BigSUV());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ActivityCarWashing_Standart.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

}
