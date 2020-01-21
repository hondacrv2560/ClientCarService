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

import com.example.client.Models.CarWashing_3Phases;
import com.example.client.Models.Toning;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ToningActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Toning
    DatabaseReference toningRef = myDbReference.child("Toning");
    TextView txtInfo;
    TextView cat1;
    TextView cat2;
    TextView cat3;
    TextView cat4;
    TextView cat5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toning_view);
        txtInfo=findViewById(R.id.txtInfo);
        cat1=findViewById(R.id.cat1);
        cat2=findViewById(R.id.cat2);
        cat3=findViewById(R.id.cat3);
        cat4=findViewById(R.id.cat4);
        cat5=findViewById(R.id.cat5);

        recyclerView = findViewById(R.id.Toning_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdToning;
        public TextView txtTitleToning;
        public TextView txtPriceToning_sedan;
        public TextView txtPriceToning_business;
        public TextView txtPriceToning_premium;
        public TextView txtPriceToning_SUV;
        public TextView txtPriceToning_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdToning = itemView.findViewById(R.id.id_title);
            txtTitleToning = itemView.findViewById(R.id.list_title);
            txtPriceToning_sedan = itemView.findViewById(R.id.priceToning_sedan);
            txtPriceToning_business = itemView.findViewById(R.id.priceToning_business);
            txtPriceToning_premium = itemView.findViewById(R.id.priceToning_premium);
            txtPriceToning_SUV = itemView.findViewById(R.id.priceToning_SUV);
            txtPriceToning_BigSUV = itemView.findViewById(R.id.priceToning_BigSUV);

        }

        public void setTxtIdToning(String string) {
            txtIdToning.setText(string);
        }
        public void setTxtTitleToning(String string) {
            txtTitleToning.setText(string);
        }
        public void setTxtPriceToning_sedan(String string){
            txtPriceToning_sedan.setText(string);
        }
        public void setTxtPriceToning_business(String string) {
            txtPriceToning_business.setText(string);
        }
        public void setTxtPriceToning_premium(String string){
            txtPriceToning_premium.setText(string);
        }
        public void setTxtPriceToning_SUV(String string) {
            txtPriceToning_SUV.setText(string);
        }
        public void setTxtPriceToning_BigSUV(String string){
            txtPriceToning_BigSUV.setText(string);
        }
    }

    private void fetch() {
        Query query = toningRef;

        FirebaseRecyclerOptions<Toning> options =
                new FirebaseRecyclerOptions.Builder<Toning>()
                        .setQuery(query, new SnapshotParser<Toning>() {
                            @NonNull
                            @Override
                            public Toning parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Toning(snapshot.child("idToning").getValue().toString(),
                                        snapshot.child("titleToning").getValue().toString(),
                                        snapshot.child("priceToning_sedan").getValue().toString(),
                                        snapshot.child("priceToning_business").getValue().toString(),
                                        snapshot.child("priceToning_premium").getValue().toString(),
                                        snapshot.child("priceToning_SUV").getValue().toString(),
                                        snapshot.child("priceToning_BigSUV").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Toning, ToningActivity.ViewHolder>(options) {

            @Override
            public ToningActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.toning_list, parent, false);

                return new ToningActivity.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ToningActivity.ViewHolder holder, final int position, Toning toning) {
                holder.setTxtIdToning(toning.getIdToning());
                holder.setTxtTitleToning(toning.getTitleToning());
                holder.setTxtPriceToning_sedan(toning.getPriceToning_sedan());
                holder.setTxtPriceToning_business(toning.getPriceToning_business());
                holder.setTxtPriceToning_premium(toning.getPriceToning_premium());
                holder.setTxtPriceToning_SUV(toning.getPriceToning_SUV());
                holder.setTxtPriceToning_BigSUV(toning.getPriceToning_BigSUV());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ToningActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
