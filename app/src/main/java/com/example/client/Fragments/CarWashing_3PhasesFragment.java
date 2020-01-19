package com.example.client.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
