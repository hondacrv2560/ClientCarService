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
import com.example.client.Models.ChemicalCleaningSalon;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChemicalCleaningSalonActivivy extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child ChemicalCleaningSalon
    DatabaseReference chemicalCleaningSalonRef = myDbReference.child("ChemicalCleaningSalon");
    TextView txtInfo;
    TextView cat1;
    TextView cat2;
    TextView cat3;
    TextView cat4;
    TextView cat5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chemical_cleaning_salon_view);
        txtInfo=findViewById(R.id.txtInfo);
        cat1=findViewById(R.id.cat1);
        cat2=findViewById(R.id.cat2);
        cat3=findViewById(R.id.cat3);
        cat4=findViewById(R.id.cat4);
        cat5=findViewById(R.id.cat5);

        recyclerView = findViewById(R.id.ChemicalCleaningSalon_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdChemicalCleaningSalon;
        public TextView txtTitleChemicalCleaningSalon;
        public TextView txtPriceChemicalCleaningSalon_sedan;
        public TextView txtPriceChemicalCleaningSalon_business;
        public TextView txtPriceChemicalCleaningSalon_premium;
        public TextView txtPriceChemicalCleaningSalon_SUV;
        public TextView txtPriceChemicalCleaningSalon_BigSUV;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdChemicalCleaningSalon = itemView.findViewById(R.id.id_title);
            txtTitleChemicalCleaningSalon = itemView.findViewById(R.id.list_title);
            txtPriceChemicalCleaningSalon_sedan = itemView.findViewById(R.id.priceChemicalCleaningSalon_sedan);
            txtPriceChemicalCleaningSalon_business = itemView.findViewById(R.id.priceChemicalCleaningSalon_business);
            txtPriceChemicalCleaningSalon_premium = itemView.findViewById(R.id.priceChemicalCleaningSalon_premium);
            txtPriceChemicalCleaningSalon_SUV = itemView.findViewById(R.id.priceChemicalCleaningSalon_SUV);
            txtPriceChemicalCleaningSalon_BigSUV = itemView.findViewById(R.id.priceChemicalCleaningSalon_BigSUV);

        }

        public void setTxtIdChemicalCleaningSalon(String string) {
            txtIdChemicalCleaningSalon.setText(string);
        }
        public void setTxtTitleChemicalCleaningSalon(String string) {
            txtTitleChemicalCleaningSalon.setText(string);
        }
        public void setTxtPriceChemicalCleaningSalon_sedan(String string){
            txtPriceChemicalCleaningSalon_sedan.setText(string);
        }
        public void setTxtPriceChemicalCleaningSalon_business(String string) {
            txtPriceChemicalCleaningSalon_business.setText(string);
        }
        public void setTxtPriceChemicalCleaningSalon_premium(String string){
            txtPriceChemicalCleaningSalon_premium.setText(string);
        }
        public void setTxtPriceChemicalCleaningSalon_SUV(String string) {
            txtPriceChemicalCleaningSalon_SUV.setText(string);
        }
        public void setTxtPriceChemicalCleaningSalon_BigSUV(String string){
            txtPriceChemicalCleaningSalon_BigSUV.setText(string);
        }
    }

    private void fetch() {
        Query query = chemicalCleaningSalonRef;

        FirebaseRecyclerOptions<ChemicalCleaningSalon> options =
                new FirebaseRecyclerOptions.Builder<ChemicalCleaningSalon>()
                        .setQuery(query, new SnapshotParser<ChemicalCleaningSalon>() {
                            @NonNull
                            @Override
                            public ChemicalCleaningSalon parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new ChemicalCleaningSalon(snapshot.child("idChemicalCleaningSalon").getValue().toString(),
                                        snapshot.child("titleChemicalCleaningSalon").getValue().toString(),
                                        snapshot.child("priceChemicalCleaningSalon_sedan").getValue().toString(),
                                        snapshot.child("priceChemicalCleaningSalon_business").getValue().toString(),
                                        snapshot.child("priceChemicalCleaningSalon_premium").getValue().toString(),
                                        snapshot.child("priceChemicalCleaningSalon_SUV").getValue().toString(),
                                        snapshot.child("priceChemicalCleaningSalon_BigSUV").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<ChemicalCleaningSalon, ChemicalCleaningSalonActivivy.ViewHolder>(options) {

            @Override
            public ChemicalCleaningSalonActivivy.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chemical_cleaning_salon_list, parent, false);

                return new ChemicalCleaningSalonActivivy.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ChemicalCleaningSalonActivivy.ViewHolder holder, final int position, ChemicalCleaningSalon chemicalCleaningSalon) {
                holder.setTxtIdChemicalCleaningSalon(chemicalCleaningSalon.getIdChemicalCleaningSalon());
                holder.setTxtTitleChemicalCleaningSalon(chemicalCleaningSalon.getTitleChemicalCleaningSalon());
                holder.setTxtPriceChemicalCleaningSalon_sedan(chemicalCleaningSalon.getPriceChemicalCleaningSalon_sedan());
                holder.setTxtPriceChemicalCleaningSalon_business(chemicalCleaningSalon.getPriceChemicalCleaningSalon_business());
                holder.setTxtPriceChemicalCleaningSalon_premium(chemicalCleaningSalon.getPriceChemicalCleaningSalon_premium());
                holder.setTxtPriceChemicalCleaningSalon_SUV(chemicalCleaningSalon.getPriceChemicalCleaningSalon_SUV());
                holder.setTxtPriceChemicalCleaningSalon_BigSUV(chemicalCleaningSalon.getPriceChemicalCleaningSalon_BigSUV());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ChemicalCleaningSalonActivivy.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
