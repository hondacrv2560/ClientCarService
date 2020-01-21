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

public class NanoCeramicsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child NanoCeramics
    DatabaseReference nanoCeramicsRef = myDbReference.child("NanoCeramics");
    TextView txtInfo;
    TextView cat1;
    TextView cat2;
    TextView cat3;
    TextView cat4;
    TextView cat5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nano_ceramics_view);

        txtInfo=findViewById(R.id.txtInfo);
        cat1=findViewById(R.id.cat1);
        cat2=findViewById(R.id.cat2);
        cat3=findViewById(R.id.cat3);
        cat4=findViewById(R.id.cat4);
        cat5=findViewById(R.id.cat5);

        recyclerView = findViewById(R.id.CarWashing_3Phases_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdNanoCeramics;
        public TextView txtTitleNanoCeramics;
        public TextView txtPriceNanoCeramics_sedan;
        public TextView txtPriceNanoCeramics_business;
        public TextView txtPriceNanoCeramics_premium;
        public TextView txtPriceNanoCeramics_SUV;
        public TextView txtPriceNanoCeramics_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdNanoCeramics = itemView.findViewById(R.id.id_title);
            txtTitleNanoCeramics = itemView.findViewById(R.id.list_title);
            txtPriceNanoCeramics_sedan = itemView.findViewById(R.id.priceNanoCeramics_sedan);
            txtPriceNanoCeramics_business = itemView.findViewById(R.id.priceNanoCeramics_business);
            txtPriceNanoCeramics_premium = itemView.findViewById(R.id.priceNanoCeramics_premium);
            txtPriceNanoCeramics_SUV = itemView.findViewById(R.id.priceNanoCeramics_SUV);
            txtPriceNanoCeramics_BigSUV = itemView.findViewById(R.id.priceNanoCeramics_BigSUV);

        }

        public void setTxtIdNanoCeramics(String string) {
            txtIdNanoCeramics.setText(string);
        }
        public void setTxtTitleNanoCeramics(String string) {
            txtTitleNanoCeramics.setText(string);
        }
        public void setTxtPriceNanoCeramics_sedan(String string){
            txtPriceNanoCeramics_sedan.setText(string);
        }
        public void setTxtPriceNanoCeramics_business(String string) {
            txtPriceNanoCeramics_business.setText(string);
        }
        public void setTxtPriceNanoCeramics_premium(String string){
            txtPriceNanoCeramics_premium.setText(string);
        }
        public void setTxtPriceNanoCeramics_SUV(String string) {
            txtPriceNanoCeramics_SUV.setText(string);
        }
        public void setTxtPriceNanoCeramics_BigSUV(String string){
            txtPriceNanoCeramics_BigSUV.setText(string);
        }
    }
}
