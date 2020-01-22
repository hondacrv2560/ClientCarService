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

import com.example.client.Models.SalonProtection;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SalonProtectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child SalonProtection
    DatabaseReference salonProtectionRef = myDbReference.child("SalonProtection");
    TextView txtInfo;
    TextView cat1;
    TextView cat2;
    TextView cat3;
    TextView cat4;
    TextView cat5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_protection_list);
        txtInfo=findViewById(R.id.txtInfo);
        cat1=findViewById(R.id.cat1);
        cat2=findViewById(R.id.cat2);
        cat3=findViewById(R.id.cat3);
        cat4=findViewById(R.id.cat4);
        cat5=findViewById(R.id.cat5);

        recyclerView = findViewById(R.id.SalonProtection_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdSalonProtection;
        public TextView txtTitleSalonProtection;
        public TextView txtPriceSalonProtection_sedan;
        public TextView txtPriceSalonProtection_business;
        public TextView txtPriceSalonProtection_premium;
        public TextView txtPriceSalonProtection_SUV;
        public TextView txtPriceSalonProtection_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdSalonProtection = itemView.findViewById(R.id.id_title);
            txtTitleSalonProtection = itemView.findViewById(R.id.list_title);
            txtPriceSalonProtection_sedan = itemView.findViewById(R.id.priceSalonProtection_sedan);
            txtPriceSalonProtection_business = itemView.findViewById(R.id.priceSalonProtection_business);
            txtPriceSalonProtection_premium = itemView.findViewById(R.id.priceSalonProtection_premium);
            txtPriceSalonProtection_SUV = itemView.findViewById(R.id.priceSalonProtection_SUV);
            txtPriceSalonProtection_BigSUV = itemView.findViewById(R.id.priceSalonProtection_BigSUV);

        }

        public void setTxtIdSalonProtection(String string) {
            txtIdSalonProtection.setText(string);
        }

        public void setTxtTitleSalonProtection(String string) {
            txtTitleSalonProtection.setText(string);
        }

        public void setTxtPriceSalonProtection_sedan(String string) {
            txtPriceSalonProtection_sedan.setText(string);
        }

        public void setTxtPriceSalonProtection_business(String string) {
            txtPriceSalonProtection_business.setText(string);
        }

        public void setTxtPriceSalonProtection_premium(String string) {
            txtPriceSalonProtection_premium.setText(string);
        }

        public void setTxtPriceSalonProtection_SUV(String string) {
            txtPriceSalonProtection_SUV.setText(string);
        }

        public void setTxtPriceSalonProtection_BigSUV(String string) {
            txtPriceSalonProtection_BigSUV.setText(string);
        }
    }

    private void fetch() {
        Query query = salonProtectionRef;

        FirebaseRecyclerOptions<SalonProtection> options =
                new FirebaseRecyclerOptions.Builder<SalonProtection>()
                        .setQuery(query, new SnapshotParser<SalonProtection>() {
                            @NonNull
                            @Override
                            public SalonProtection parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new SalonProtection(snapshot.child("idSalonProtection").getValue().toString(),
                                        snapshot.child("titleSalonProtection").getValue().toString(),
                                        snapshot.child("priceSalonProtection_sedan").getValue().toString(),
                                        snapshot.child("priceSalonProtection_business").getValue().toString(),
                                        snapshot.child("priceSalonProtection_premium").getValue().toString(),
                                        snapshot.child("priceSalonProtection_SUV").getValue().toString(),
                                        snapshot.child("priceSalonProtection_BigSUV").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<SalonProtection, SalonProtectionActivity.ViewHolder>(options) {

            @Override
            public SalonProtectionActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.salon_protection_list, parent, false);

                return new SalonProtectionActivity.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(SalonProtectionActivity.ViewHolder holder, final int position, SalonProtection salonProtection) {
                holder.setTxtIdSalonProtection(salonProtection.getIdSalonProtection());
                holder.setTxtTitleSalonProtection(salonProtection.getTitleSalonProtection());
                holder.setTxtPriceSalonProtection_sedan(salonProtection.getPriceSalonProtection_sedan());
                holder.setTxtPriceSalonProtection_business(salonProtection.getPriceSalonProtection_business());
                holder.setTxtPriceSalonProtection_premium(salonProtection.getPriceSalonProtection_premium());
                holder.setTxtPriceSalonProtection_SUV(salonProtection.getPriceSalonProtection_SUV());
                holder.setTxtPriceSalonProtection_BigSUV(salonProtection.getPriceSalonProtection_BigSUV());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SalonProtectionActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
