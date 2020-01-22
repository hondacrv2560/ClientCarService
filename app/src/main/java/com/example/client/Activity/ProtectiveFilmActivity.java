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

import com.example.client.Models.ProtectiveFilm;
import com.example.client.Models.ProtectiveFilm;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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

    private void fetch() {
        Query query = protectiveFilmRef;

        FirebaseRecyclerOptions<ProtectiveFilm> options =
                new FirebaseRecyclerOptions.Builder<ProtectiveFilm>()
                        .setQuery(query, new SnapshotParser<ProtectiveFilm>() {
                            @NonNull
                            @Override
                            public ProtectiveFilm parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new ProtectiveFilm(snapshot.child("idProtectiveFilm").getValue().toString(),
                                        snapshot.child("titleProtectiveFilm").getValue().toString(),
                                        snapshot.child("priceProtectiveFilm_sedan").getValue().toString(),
                                        snapshot.child("priceProtectiveFilm_business").getValue().toString(),
                                        snapshot.child("priceProtectiveFilm_premium").getValue().toString(),
                                        snapshot.child("priceProtectiveFilm_SUV").getValue().toString(),
                                        snapshot.child("priceProtectiveFilm_BigSUV").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<ProtectiveFilm, ProtectiveFilmActivity.ViewHolder>(options) {

            @Override
            public ProtectiveFilmActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.protective_film_list, parent, false);

                return new ProtectiveFilmActivity.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ProtectiveFilmActivity.ViewHolder holder, final int position, ProtectiveFilm protectiveFilm) {
                holder.setTxtIdProtectiveFilm(protectiveFilm.getIdProtectiveFilm());
                holder.setTxtTitleProtectiveFilm(protectiveFilm.getTitleProtectiveFilm());
                holder.setTxtPriceProtectiveFilm_sedan(protectiveFilm.getPriceProtectiveFilm_sedan());
                holder.setTxtPriceProtectiveFilm_business(protectiveFilm.getPriceProtectiveFilm_business());
                holder.setTxtPriceProtectiveFilm_premium(protectiveFilm.getPriceProtectiveFilm_premium());
                holder.setTxtPriceProtectiveFilm_SUV(protectiveFilm.getPriceProtectiveFilm_SUV());
                holder.setTxtPriceProtectiveFilm_BigSUV(protectiveFilm.getPriceProtectiveFilm_BigSUV());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ProtectiveFilmActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
