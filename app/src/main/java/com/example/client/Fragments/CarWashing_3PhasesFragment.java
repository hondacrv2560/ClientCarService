package com.example.client.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Models.CarWashing_3Phases;
import com.example.client.Models.Service;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CarWashing_3PhasesFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Orders
    DatabaseReference serviceRef = myDbReference.child("CarWashing_3Phases");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View carWashing_3PhasesFragment = inflater.inflate(R.layout.car_washing_3phases_view, container, false);

        recyclerView = carWashing_3PhasesFragment.findViewById(R.id.CarWashing_3Phases_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

        return carWashing_3PhasesFragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdCw_3Phases;
        public TextView txtTitleCw_3Phases;
        public TextView txtPriceCw_3Phases_sedan;
        public TextView txtPriceCw_3Phases_business;
        public TextView txtPriceCw_3Phases_premium;
        public TextView txtPriceCw_3Phases_SUV;
        public TextView txtPriceCw_3Phases_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdCw_3Phases = itemView.findViewById(R.id.id_title);
            txtTitleCw_3Phases = itemView.findViewById(R.id.list_title);
            txtPriceCw_3Phases_sedan = itemView.findViewById(R.id.priceCw_sedan);
            txtPriceCw_3Phases_business = itemView.findViewById(R.id.priceCw_business);
            txtPriceCw_3Phases_premium = itemView.findViewById(R.id.priceCw_premium);
            txtPriceCw_3Phases_SUV = itemView.findViewById(R.id.priceCw_SUV);
            txtPriceCw_3Phases_BigSUV = itemView.findViewById(R.id.priceCw_BigSUV);

        }

        public void setTxtIdCw_3Phases(String string) {
            txtIdCw_3Phases.setText(string);
        }
        public void setTxtTitleCw_3Phases(String string) {
            txtTitleCw_3Phases.setText(string);
        }
        public void setTxtPriceCw_3Phases_sedan(String string){
            txtPriceCw_3Phases_sedan.setText(string);
        }
        public void setTxtPriceCw_3Phases_business(String string) {
            txtPriceCw_3Phases_business.setText(string);
        }
        public void setTxtPriceCw_3Phases_premium(String string){
            txtPriceCw_3Phases_premium.setText(string);
        }
        public void setTxtPriceCw_3Phases_SUV(String string) {
            txtPriceCw_3Phases_SUV.setText(string);
        }
        public void setTxtPriceCw_3Phases_BigSUV(String string){
            txtPriceCw_3Phases_BigSUV.setText(string);
        }
    }

    private void fetch() {
        Query query = myDbReference.child("CarWashing_3Phases");

        FirebaseRecyclerOptions<CarWashing_3Phases> options =
                new FirebaseRecyclerOptions.Builder<CarWashing_3Phases>()
                        .setQuery(query, new SnapshotParser<CarWashing_3Phases>() {
                            @NonNull
                            @Override
                            public CarWashing_3Phases parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new CarWashing_3Phases(snapshot.child("idCw_3Phases").getValue().toString(),
                                        snapshot.child("titleCw_3Phases").getValue().toString(),
                                        snapshot.child("priceCw_3Phases_sedan").getValue().toString(),
                                        snapshot.child("priceCw_3Phases_business").getValue().toString(),
                                        snapshot.child("priceCw_3Phases_premium").getValue().toString(),
                                        snapshot.child("priceCw_3Phases_SUV").getValue().toString(),
                                        snapshot.child("priceCw_3Phases_BigSUV").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<CarWashing_3Phases, CarWashing_3PhasesFragment.ViewHolder>(options) {

            @Override
            public CarWashing_3PhasesFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.service_list, parent, false);

                return new CarWashing_3PhasesFragment.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(CarWashing_3PhasesFragment.ViewHolder holder, final int position, CarWashing_3Phases carWashing3Phases) {
                holder.setTxtIdCw_3Phases(carWashing3Phases.getIdCw_3Phases());
                holder.setTxtTitleCw_3Phases(carWashing3Phases.getTitleCw_3Phases());
                holder.setTxtPriceCw_3Phases_sedan(carWashing3Phases.getPriceCw_3Phases_sedan());
                holder.setTxtPriceCw_3Phases_business(carWashing3Phases.getPriceCw_3Phases_business());
                holder.setTxtPriceCw_3Phases_premium(carWashing3Phases.getPriceCw_3Phases_premium());
                holder.setTxtPriceCw_3Phases_SUV(carWashing3Phases.getPriceCw_3Phases_SUV());
                holder.setTxtPriceCw_3Phases_BigSUV(carWashing3Phases.getPriceCw_3Phases_BigSUV());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }
}