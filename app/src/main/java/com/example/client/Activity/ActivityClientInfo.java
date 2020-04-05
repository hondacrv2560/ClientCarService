package com.example.client.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.ClientInfoAdapter;
import com.example.client.Models.Client;
import com.example.client.Models.ClientInfo;
import com.example.client.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
    private Activity activityClientInfo;
    private FirebaseAuth firebaseAuth;
    DatabaseReference dbInfoClient;

    Button addCar;
    ImageView qrCode;
    Button btnOk;
    EditText userAuth;
    EditText passAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info_view);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        activityClientInfo = ActivityClientInfo.this;


        btnOk = findViewById(R.id.okBtn);
        userAuth = findViewById(R.id.authUser);
        passAuth = findViewById(R.id.authPass);
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
            btnOk.setVisibility(View.INVISIBLE);
            userAuth.setVisibility(View.INVISIBLE);
            passAuth.setVisibility(View.INVISIBLE);
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
            qrCode.setVisibility(View.INVISIBLE);
            addCar.setVisibility(View.INVISIBLE);
            Toast.makeText(ActivityClientInfo.this, "пожалуйста пройдите авторизацию", Toast.LENGTH_LONG).show();
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(userAuth.getText().toString(), passAuth.getText().toString());
                restartActivity(activityClientInfo);
            }
        });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // вход зарегистрированного клиента
    public void signIn(String e_mail, String pass){
        firebaseAuth.signInWithEmailAndPassword(e_mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ActivityClientInfo.this, "Ok", Toast.LENGTH_SHORT).show();
                }else{
                    String TAG="test";
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(ActivityClientInfo.this, "Bad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void restartActivity(Activity activity){
        activity.recreate();
    }
}
