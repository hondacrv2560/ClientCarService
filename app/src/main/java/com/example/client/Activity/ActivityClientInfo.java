package com.example.client.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.ClientInfoAdapter;
import com.example.client.Models.Client;
import com.example.client.Models.ClientInfo;
import com.example.client.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class ActivityClientInfo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClientInfoAdapter clientInfoAdapter;
    private ArrayList<ClientInfo> clientInfoList;

    private FirebaseAuth firebaseAuth;
    DatabaseReference dbInfoClient;

    Button addCar;
    ImageView qrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info_view);

        addCar = findViewById(R.id.addCar);
        qrCode = findViewById(R.id.qrCodeView);
        recyclerView = findViewById(R.id.clientInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientInfoList = new ArrayList<ClientInfo>();
        clientInfoAdapter = new ClientInfoAdapter(this, clientInfoList);
        recyclerView.setAdapter(clientInfoAdapter);
        dbInfoClient = FirebaseDatabase.getInstance().getReference("Clients");

        firebaseAuth = firebaseAuth.getInstance();
        // gjkextv Uid user
        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if(user != null){
//            Query query = dbInfoClient
//                    .orderByChild("UserId")
//                    .equalTo(user.getUid());
//            query.addListenerForSingleValueEvent(valueEventListener);
//            Toast.makeText(ActivityClientInfo.this, "signed in" + user.getUid(), Toast.LENGTH_SHORT).show();
//        } else{
//            Toast.makeText(ActivityClientInfo.this, "пожалуйста пройдите авторизацию" + user.getUid(), Toast.LENGTH_LONG).show();
//        }

        try {
            Query query = dbInfoClient
                    .orderByChild("UserId")
                    .equalTo(user.getUid());
            query.addListenerForSingleValueEvent(valueEventListener);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityClientInfo.this, "пожалуйста пройдите авторизацию", Toast.LENGTH_LONG).show();
        }


        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCar = new Intent(ActivityClientInfo.this, RegularClientActivity.class);
                startActivity(addCar);
            }
        });

        if (user!=null){
            String getUserId= user.getUid();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(getUserId, BarcodeFormat.QR_CODE, 300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qrCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        } else{
            addCar.setEnabled(false);
            Toast.makeText(ActivityClientInfo.this, "пожалуйста пройдите авторизацию", Toast.LENGTH_LONG).show();
        }
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            clientInfoList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClientInfo clientInfo = snapshot.getValue(ClientInfo.class);
                    clientInfoList.add(clientInfo);
                }
                clientInfoAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
