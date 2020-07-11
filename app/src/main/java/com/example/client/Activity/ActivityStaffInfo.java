package com.example.client.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.ClientInfoAdapter;
import com.example.client.Classes.StaffInfoAdapter;
import com.example.client.Models.ClientInfo;
import com.example.client.Models.Staff;
import com.example.client.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityStaffInfo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaffInfoAdapter staffInfoAdapter;
    private ArrayList<Staff> staffInfoList;

    DatabaseReference dbInfoStaff;
    TextView addStaff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_view);

        addStaff = findViewById(R.id.addStaff);
        recyclerView = findViewById(R.id.infoStaff_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        staffInfoList = new ArrayList<Staff>();
        staffInfoAdapter = new StaffInfoAdapter(this, staffInfoList);
        recyclerView.setAdapter(staffInfoAdapter);
        dbInfoStaff = FirebaseDatabase.getInstance().getReference("Staff");

        Query query = dbInfoStaff;
        query.addListenerForSingleValueEvent(valueEventListener);
        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityStaffInfo.this, ActivivtyAddStaff.class);
                startActivity(intent);
                finish();
            }
        });

    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            staffInfoList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Staff staff = snapshot.getValue(Staff.class);
                    staffInfoList.add(staff);
                }
                staffInfoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
