package com.example.client.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.client.Classes.ClientInfoAdapter;
import com.example.client.Models.ClientInfo;
import com.example.client.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.util.ArrayList;

public class ActivityClientInfo extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private RecyclerView recyclerView;
    private ClientInfoAdapter clientInfoAdapter;
    private ArrayList<ClientInfo> clientInfoList;
    EditText find_Client_By_Name;
    EditText find_Client_Gov_Number;
    TextView find_Client_Qr_Code;
    Button btn_findNameSurname;
    Button btn_findGovNumber;
    Button btn_findQrCode;
    DatabaseReference dbInfoClient;
    Button addCar;
    String qrCode;

    private static String strQrReaderCode;
    private CodeScanner codeScanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info_view);
        final Activity activity = this;

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        qrCode = result.getText();
                        find_Client_Qr_Code.setText(qrCode);
                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
        qrCode = QrReaderFragment.getQrCode();

        find_Client_By_Name = findViewById(R.id.findClientByName);
        find_Client_Gov_Number = findViewById(R.id.findClientGovNumber);
        find_Client_Qr_Code = findViewById(R.id.findClientQrCode);
        btn_findNameSurname = findViewById(R.id.findNameSurname);
        btn_findGovNumber = findViewById(R.id.findGovNumber);
        btn_findQrCode = findViewById(R.id.findQrCode);
        addCar = findViewById(R.id.addCar);
        recyclerView = findViewById(R.id.clientInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientInfoList = new ArrayList<ClientInfo>();
        clientInfoAdapter = new ClientInfoAdapter(this, clientInfoList);
        recyclerView.setAdapter(clientInfoAdapter);
        dbInfoClient = FirebaseDatabase.getInstance().getReference("Clients");

        find_Client_Qr_Code.setText(qrCode);
        btn_findNameSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameSurnameClient = find_Client_By_Name.getText().toString();
                Query query = dbInfoClient
                        .orderByChild("nameSurnameNewClient")
                        .equalTo(nameSurnameClient);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });
        btn_findGovNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String govNumber = find_Client_Gov_Number.getText().toString();
                Query query = dbInfoClient
                        .orderByChild("govNumberCarNewClient")
                        .equalTo(govNumber);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });

        btn_findQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qr = find_Client_Qr_Code.getText().toString();
                Query query = dbInfoClient
                        .orderByChild("UserId")
                        .equalTo(qr);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCar = new Intent(ActivityClientInfo.this, RegularClientActivity.class);
                startActivity(addCar);
            }
        });

//        String getUserId= user.getUid();
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try {
//            BitMatrix bitMatrix = multiFormatWriter.encode(getUserId, BarcodeFormat.QR_CODE, 300,300);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//            qrCode.setImageBitmap(bitmap);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }

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

//
//https://github.com/yuriy-budiyev/code-scanner