package com.example.client.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class ActivitySearchClient extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchClientInfoAdapter searchClientInfoAdapter;
    private ArrayList<SearchClientInfo> searchClientInfoArrayList;

    DatabaseReference dbSearchClient;
    Button searchClient;
    EditText govNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_client_view);

        govNum = findViewById(R.id.govNumCarClient);
        searchClient = findViewById(R.id.searchGovNum);
        recyclerView = findViewById(R.id.searchClientList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchClientInfoArrayList = new ArrayList<SearchClientInfo>();
        searchClientInfoAdapter = new SearchClientInfoAdapter(this, searchClientInfoArrayList);
        recyclerView.setAdapter(searchClientInfoAdapter);

        dbSearchClient = FirebaseDatabase.getInstance().getReference("Orders");

        searchClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String govNumber = govNum.getText().toString();
                Query query = dbSearchClient
                        .orderByChild("carGovNumber")
                        .equalTo(govNumber);
                query.addListenerForSingleValueEvent(valueEventListener);
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
                    searchClientInfoArrayList.add(clientInfo);
                }
                searchClientInfoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void SearchClient(){
        Query query = dbSearchClient
        .orderByChild("carGovNumber")
                .equalTo("AA4422KB");
        query.addListenerForSingleValueEvent(valueEventListener);
    }
}
