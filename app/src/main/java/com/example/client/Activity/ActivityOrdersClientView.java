package com.example.client.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.OrdersClientViewAdapter;
import com.example.client.Models.OrderViewClient;
import com.example.client.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityOrdersClientView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrdersClientViewAdapter ordersClientViewAdapter;
    private ArrayList<OrderViewClient> orderViewClientList;

    DatabaseReference dbOrders;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_client_view);

        recyclerView = findViewById(R.id.orderList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderViewClientList = new ArrayList<OrderViewClient>();
        ordersClientViewAdapter = new OrdersClientViewAdapter(this, orderViewClientList);
        recyclerView.setAdapter(ordersClientViewAdapter);
        dbOrders = FirebaseDatabase.getInstance().getReference("Orders");

        Query query = dbOrders
                .orderByChild("UserId")
                .equalTo("COgoqdE0WnPOl4vHqxDxkpm12Ai1");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            orderViewClientList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderViewClient orderViewClient = snapshot.getValue(OrderViewClient.class);
                    orderViewClientList.add(orderViewClient);
                }
                ordersClientViewAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
