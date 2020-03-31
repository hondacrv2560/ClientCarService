package com.example.client.Activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    protected void onStart() {
        super.onStart();
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


}
