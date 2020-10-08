package com.example.client.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.SearchClientInfoAdapter;
import com.example.client.Models.SearchClientInfo;
import com.example.client.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivitySearchClient extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchClientInfoAdapter searchClientInfoAdapter;
    private ArrayList<SearchClientInfo> searchClientInfoArrayList;

    DatabaseReference dbSearchClient;
    EditText searchClient;
    TextView govNum;
    TextView dateOder;
    TextView phone;

    Calendar calendar;
    int startOrderDay;
    int startOrderMonth;
    int startOrderYear;
    public String date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_client_view);

        calendar = Calendar.getInstance();
        govNum = findViewById(R.id.searchGovNum);
        searchClient = findViewById(R.id.govNumCarClient);
        phone = findViewById(R.id.phoneNumClient);
        dateOder = findViewById(R.id.dateOder);
        recyclerView = findViewById(R.id.searchClientList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchClientInfoArrayList = new ArrayList<SearchClientInfo>();
        searchClientInfoAdapter = new SearchClientInfoAdapter(this, searchClientInfoArrayList);
        recyclerView.setAdapter(searchClientInfoAdapter);
        setUpperCase();

        dbSearchClient = FirebaseDatabase.getInstance().getReference("Orders");

        govNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String govNumber = searchClient.getText().toString();
                Query query = dbSearchClient
                        .orderByChild("carGovNumber")
                        .equalTo(govNumber);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });

        startOrderDay = calendar.get (Calendar.DAY_OF_MONTH);
        startOrderMonth= calendar.get (Calendar.MONTH)+1;
        startOrderYear = calendar.get (Calendar.YEAR);

       date  = "Сегодня: "+startOrderDay+"."+ startOrderMonth+"."+startOrderYear;
       dateOder.setText(date);

        phone.setOnClickListener(v -> {
            Toast.makeText(ActivitySearchClient.this, "test", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone.getText().toString()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            searchClientInfoArrayList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    SearchClientInfo clientInfo = snapshot.getValue(SearchClientInfo.class);
                    if (clientInfo.startDayOfMonth==startOrderDay) {
                        searchClientInfoArrayList.add(clientInfo);
                        phone.setText(clientInfo.phoneNumberClient);
                    }
                }
                searchClientInfoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void setUpperCase(){
        searchClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strGovNumber = s.toString();
                if(!strGovNumber.equals(strGovNumber.toUpperCase())){
                    strGovNumber = strGovNumber.toUpperCase();
                    searchClient.setText(strGovNumber);
                }
                searchClient.setSelection(searchClient.getText().length());
            }
        });
    }
}
