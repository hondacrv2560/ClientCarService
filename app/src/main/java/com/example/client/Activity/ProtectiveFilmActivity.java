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

public class ProtectiveFilmActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child ProtectiveFilm
    DatabaseReference protectiveFilmRef = myDbReference.child("ProtectiveFilm");
    TextView txtInfo;
    TextView cat1;
    TextView cat2;
    TextView cat3;
    TextView cat4;
    TextView cat5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protective_film_view);

        txtInfo=findViewById(R.id.txtInfo);
        cat1=findViewById(R.id.cat1);
        cat2=findViewById(R.id.cat2);
        cat3=findViewById(R.id.cat3);
        cat4=findViewById(R.id.cat4);
        cat5=findViewById(R.id.cat5);

        recyclerView = findViewById(R.id.ProtectiveFilm_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdProtectiveFilm;
        public TextView txtTitleProtectiveFilm;
        public TextView txtPriceProtectiveFilm_sedan;
        public TextView txtPriceProtectiveFilm_business;
        public TextView txtPriceProtectiveFilm_premium;
        public TextView txtPriceProtectiveFilm_SUV;
        public TextView txtPriceProtectiveFilm_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdProtectiveFilm = itemView.findViewById(R.id.id_title);
            txtTitleProtectiveFilm = itemView.findViewById(R.id.list_title);
            txtPriceProtectiveFilm_sedan = itemView.findViewById(R.id.priceProtectiveFilm_sedan);
            txtPriceProtectiveFilm_business = itemView.findViewById(R.id.priceProtectiveFilm_business);
            txtPriceProtectiveFilm_premium = itemView.findViewById(R.id.priceProtectiveFilm_premium);
            txtPriceProtectiveFilm_SUV = itemView.findViewById(R.id.priceProtectiveFilm_SUV);
            txtPriceProtectiveFilm_BigSUV = itemView.findViewById(R.id.priceProtectiveFilm_BigSUV);

        }

        public void setTxtIdProtectiveFilm(String string) {
            txtIdProtectiveFilm.setText(string);
        }
        public void setTxtTitleProtectiveFilm(String string) {
            txtTitleProtectiveFilm.setText(string);
        }
        public void setTxtPriceProtectiveFilm_sedan(String string){
            txtPriceProtectiveFilm_sedan.setText(string);
        }
        public void setTxtPriceProtectiveFilm_business(String string) {
            txtPriceProtectiveFilm_business.setText(string);
        }
        public void setTxtPriceProtectiveFilm_premium(String string){
            txtPriceProtectiveFilm_premium.setText(string);
        }
        public void setTxtPriceProtectiveFilm_SUV(String string) {
            txtPriceProtectiveFilm_SUV.setText(string);
        }
        public void setTxtPriceProtectiveFilm_BigSUV(String string){
            txtPriceProtectiveFilm_BigSUV.setText(string);
        }
    }
}
