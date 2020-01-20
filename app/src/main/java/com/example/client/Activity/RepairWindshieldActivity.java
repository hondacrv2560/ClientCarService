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
}
