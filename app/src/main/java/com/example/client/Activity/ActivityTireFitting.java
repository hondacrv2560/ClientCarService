package com.example.client.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityTireFitting extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child TireFitting
    DatabaseReference tireFittingRef = myDbReference.child("TireFitting");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tire_fitting_view);

        recyclerView = findViewById(R.id.TireFitting_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdTireFitting;
        public TextView txtTitleTireFitting;
        public TextView txtPriceTireFitting_sedan;
        public TextView txtPriceTireFitting_business;
        public TextView txtPriceTireFitting_premium;
        public TextView txtPriceTireFitting_SUV;
        public TextView txtPriceTireFitting_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdTireFitting = itemView.findViewById(R.id.id_title);
            txtTitleTireFitting = itemView.findViewById(R.id.list_title);
            txtPriceTireFitting_sedan = itemView.findViewById(R.id.priceTireFitting_sedan);
            txtPriceTireFitting_business = itemView.findViewById(R.id.priceTireFitting_business);
            txtPriceTireFitting_premium = itemView.findViewById(R.id.priceTireFitting_premium);
            txtPriceTireFitting_SUV = itemView.findViewById(R.id.priceTireFitting_SUV);
            txtPriceTireFitting_BigSUV = itemView.findViewById(R.id.priceTireFitting_BigSUV);

        }

        public void setTxtIdTireFitting(String string) {
            txtIdTireFitting.setText(string);
        }
        public void setTxtTitleTireFitting(String string) {
            txtTitleTireFitting.setText(string);
        }
        public void setTxtPriceTireFitting_sedan(String string){
            txtPriceTireFitting_sedan.setText(string);
        }
        public void setTxtPriceTireFitting_business(String string) {
            txtPriceTireFitting_business.setText(string);
        }
        public void setTxtPriceTireFitting_premium(String string){
            txtPriceTireFitting_premium.setText(string);
        }
        public void setTxtPriceTireFitting_SUV(String string) {
            txtPriceTireFitting_SUV.setText(string);
        }
        public void setTxtPriceTireFitting_BigSUV(String string){
            txtPriceTireFitting_BigSUV.setText(string);
        }
    }


}
