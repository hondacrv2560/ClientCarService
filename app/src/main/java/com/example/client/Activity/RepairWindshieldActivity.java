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
import com.example.client.Models.RepairWindshield;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RepairWindshieldActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Orders
    DatabaseReference serviceRef = myDbReference.child("RepairWindshield");
    TextView txtInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_windshield_view);
        txtInfo = findViewById(R.id.txtInfo);

        recyclerView = findViewById(R.id.RepairWindshield_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdRepairWindshield;
        public TextView txtTitleRepairWindshield;
        public TextView txtPriceRepairWindshield;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdRepairWindshield = itemView.findViewById(R.id.id_title);
            txtTitleRepairWindshield = itemView.findViewById(R.id.list_title);
            txtPriceRepairWindshield = itemView.findViewById(R.id.priceRepairWindShield);
        }

        public void setTxtIdRepairWindshield(String string) {
            txtIdRepairWindshield.setText(string);
        }

        public void setTxtTitleRepairWindshield(String string) {
            txtTitleRepairWindshield.setText(string);
        }

        public void setTxtPriceRepairWindshield(String string) {
            txtPriceRepairWindshield.setText(string);
        }
    }

    private void fetch() {
        Query query = myDbReference.child("RepairWindshield");

        FirebaseRecyclerOptions<RepairWindshield> options =
                new FirebaseRecyclerOptions.Builder<RepairWindshield>()
                        .setQuery(query, new SnapshotParser<RepairWindshield>() {
                            @NonNull
                            @Override
                            public RepairWindshield parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new RepairWindshield(snapshot.child("idRepairWindshield").getValue().toString(),
                                        snapshot.child("titleRepairWindshield").getValue().toString(),
                                        snapshot.child("priceRepairWindshield").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<RepairWindshield, RepairWindshieldActivity.ViewHolder>(options) {

            @Override
            public RepairWindshieldActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.car_washing_3phases_list, parent, false);

                return new RepairWindshieldActivity.ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(RepairWindshieldActivity.ViewHolder holder, final int position, RepairWindshield repairWindshield) {
                holder.setTxtIdRepairWindshield(repairWindshield.getIdRepairWindshield());
                holder.setTxtTitleRepairWindshield(repairWindshield.getTitleRepairWindshield());
                holder.setTxtPriceRepairWindshield(repairWindshield.getPriceRepairWindshield());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(RepairWindshieldActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
