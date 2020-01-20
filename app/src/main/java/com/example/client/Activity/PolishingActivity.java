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

public class PolishingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Polishing
    DatabaseReference serviceRef = myDbReference.child("Polishing");
    TextView txtInfo;
    TextView txtInfo1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polishing_view);
        txtInfo = findViewById(R.id.txtInfo);
        txtInfo1 = findViewById(R.id.txtInfo1);

        recyclerView = findViewById(R.id.RepairWindshield_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdPolishing;
        public TextView txtTitlePolishing;
        public TextView txtPricePolishing_sedan;
        public TextView txtPricePolishing_business;
        public TextView txtPricePolishing_premium;
        public TextView txtPricePolishing_SUV;
        public TextView txtPricePolishing_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdPolishing = itemView.findViewById(R.id.id_title);
            txtTitlePolishing = itemView.findViewById(R.id.list_title);
            txtPricePolishing_sedan = itemView.findViewById(R.id.pricePolishing_sedan);
            txtPricePolishing_business = itemView.findViewById(R.id.pricePolishing_business);
            txtPricePolishing_premium = itemView.findViewById(R.id.pricePolishing_premium);
            txtPricePolishing_SUV = itemView.findViewById(R.id.pricePolishing_SUV);
            txtPricePolishing_BigSUV = itemView.findViewById(R.id.pricePolishing_BigSUV);

        }

        public void setTxtIdPolishing(String string) {
            txtIdPolishing.setText(string);
        }
        public void setTxtTitlePolishing(String string) {
            txtTitlePolishing.setText(string);
        }
        public void setTxtPolishing_sedan(String string){
            txtPricePolishing_sedan.setText(string);
        }
        public void setTxtPricePolishing_business(String string) {
            txtPricePolishing_business.setText(string);
        }
        public void setTxtPricePolishing_premium(String string){
            txtPricePolishing_premium.setText(string);
        }
        public void setTxtPricePolishing_SUV(String string) {
            txtPricePolishing_SUV.setText(string);
        }
        public void setTxtPricePolishing_BigSUV(String string){
            txtPricePolishing_BigSUV.setText(string);
        }
    }
}
