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
                    if (orderViewClient.idService==1){
                        orderViewClient.titleService = "АВТОМОЙКА 3-Х ФАЗНАЯ";
                    }else if(orderViewClient.idService==2) {
                        orderViewClient.titleService = "АВТОМОЙКА СТАНДАРТ";
                    }else if(orderViewClient.idService==3) {
                        orderViewClient.titleService = "АВТОСТЕКЛА";
                    }else if(orderViewClient.idService==4) {
                        orderViewClient.titleService = "ПОЛИРОВКА";
                    }else if(orderViewClient.idService==5) {
                        orderViewClient.titleService = "НАНОКЕРАМИКА";
                    }else if(orderViewClient.idService==6) {
                        orderViewClient.titleService = "ЗАЩИТНАЯ ПЛЕНКА";
                    }else if(orderViewClient.idService==7) {
                        orderViewClient.titleService = "ХИМЧИСТКА САЛОНА";
                    }else if(orderViewClient.idService==8) {
                        orderViewClient.titleService = "ЗАЩИТА САЛОНА";
                    }else if(orderViewClient.idService==9) {
                        orderViewClient.titleService = "ШИНОМОНТАЖ";
                    }else if(orderViewClient.idService==10) {
                        orderViewClient.titleService = "ТОНИРОВКА";
                    }
                    
                    if (orderViewClient.startTimeMonth==1){
                        orderViewClient.month = "январь";
                    }else if(orderViewClient.startTimeMonth==2) {
                        orderViewClient.month = "февраль";
                    }else if(orderViewClient.startTimeMonth==3) {
                        orderViewClient.month = "март";
                    }else if(orderViewClient.startTimeMonth==4) {
                        orderViewClient.month = "апрель";
                    }else if(orderViewClient.startTimeMonth==5) {
                        orderViewClient.month = "май";
                    }else if(orderViewClient.startTimeMonth==6) {
                        orderViewClient.month = "июнь";
                    }else if(orderViewClient.startTimeMonth==7) {
                        orderViewClient.month = "июль";
                    }else if(orderViewClient.startTimeMonth==8) {
                        orderViewClient.month = "август";
                    }else if(orderViewClient.startTimeMonth==9) {
                        orderViewClient.month = "сентябрь";
                    }else if(orderViewClient.startTimeMonth==10) {
                        orderViewClient.month = "октябрь";
                    }else if(orderViewClient.startTimeMonth==11) {
                        orderViewClient.month = "ноябрь";
                    }else if(orderViewClient.startTimeMonth==12) {
                        orderViewClient.month = "декабрь";
                    }

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
