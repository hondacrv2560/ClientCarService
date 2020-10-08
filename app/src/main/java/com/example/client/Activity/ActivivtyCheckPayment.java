package com.example.client.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.CheckPaymentAdapter;
import com.example.client.Classes.StaffInfoAdapter;
import com.example.client.Models.FullOrders;
import com.example.client.Models.Staff;
import com.example.client.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivivtyCheckPayment extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CheckPaymentAdapter checkPaymentAdapter;
    private ArrayList<FullOrders> fullOrdersList;
    TextView sumPayment;
    int totalPrice=0;

    DatabaseReference dbFullOrders;
    Date currentDate;
    DateFormat dateFormat;
    public String dateText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_payment_view);

        currentDate = new Date();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        dateText = dateFormat.format(currentDate);

        String txtTotalSum = getIntent().getStringExtra("totalSum");

        sumPayment = findViewById(R.id.sumPayment);
        recyclerView = findViewById(R.id.check_payment_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fullOrdersList = new ArrayList<FullOrders>();
        checkPaymentAdapter = new CheckPaymentAdapter(this, fullOrdersList);
        recyclerView.setAdapter(checkPaymentAdapter);
        dbFullOrders = FirebaseDatabase.getInstance().getReference("FullOrders");

        Query query = dbFullOrders;
        query.addListenerForSingleValueEvent(valueEventListener);
//        Query query = dbFullOrders
//                .orderByChild("priceService")
//                .equalTo("-MD0N-Bk-v9-F2XC9O4u");
//        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            fullOrdersList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        FullOrders fullOrders = snap.getValue(FullOrders.class);
                        if (Integer.parseInt(fullOrders.idService)==1){
                            fullOrders.idService = "АВТОМОЙКА 3-Х ФАЗНАЯ";
                        }else if(Integer.parseInt(fullOrders.idService)==2) {
                            fullOrders.idService = "АВТОМОЙКА СТАНДАРТ";
                        }else if(Integer.parseInt(fullOrders.idService)==3) {
                            fullOrders.idService = "АВТОСТЕКЛА";
                        }else if(Integer.parseInt(fullOrders.idService)==4) {
                            fullOrders.idService = "ПОЛИРОВКА";
                        }else if(Integer.parseInt(fullOrders.idService)==5) {
                            fullOrders.idService = "НАНОКЕРАМИКА";
                        }else if(Integer.parseInt(fullOrders.idService)==6) {
                            fullOrders.idService = "ЗАЩИТНАЯ ПЛЕНКА";
                        }else if(Integer.parseInt(fullOrders.idService)==7) {
                            fullOrders.idService = "ХИМЧИСТКА САЛОНА";
                        }else if(Integer.parseInt(fullOrders.idService)==8) {
                            fullOrders.idService = "ЗАЩИТА САЛОНА";
                        }else if(Integer.parseInt(fullOrders.idService)==9) {
                            fullOrders.idService = "ШИНОМОНТАЖ";
                        }else if(Integer.parseInt(fullOrders.idService)==10) {
                            fullOrders.idService = "ТОНИРОВКА";
                        }
                        if (fullOrders.idOrder.equals(getIntent().getStringExtra("totalSum"))& fullOrders.currentDate.equals(dateText)){
                            fullOrdersList.add(fullOrders);
                        }
                    }
                }
                for(int i=0;i<fullOrdersList.size();i++){
                    totalPrice += fullOrdersList.get(i).getPriceService();
                }
                sumPayment.setText(Integer.toString(totalPrice));
                checkPaymentAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
