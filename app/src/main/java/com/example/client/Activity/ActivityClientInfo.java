package com.example.client.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.ClientInfoAdapter;
import com.example.client.Classes.ToDayActivity;
import com.example.client.MainActivity;
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
    private TextView password;
    private CheckBox showPass;
    AlertDialog.Builder builder_enter_register;
    LayoutInflater inflater_enter_register;
    Button addCar;
    ImageView qrCode;
    public FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info_view);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        activityClientInfo = ActivityClientInfo.this;

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
        user = firebaseAuth.getCurrentUser();

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCar = new Intent(ActivityClientInfo.this, RegularClientActivity.class);
                startActivity(addCar);
            }
        });

        try {
            viewQrCode();
            Query query = dbInfoClient
                    .orderByChild("UserId")
                    .equalTo(user.getUid());
            query.addListenerForSingleValueEvent(valueEventListener);
        } catch (Exception e) {
            e.printStackTrace();
            builder_enter_register = new AlertDialog.Builder(ActivityClientInfo.this);
            builder_enter_register.setTitle("Вход зарегистрированного клиента");
            inflater_enter_register = ActivityClientInfo.this.getLayoutInflater();
            final View view_enter_register = inflater_enter_register.inflate(R.layout.activity_enter_regular_client,null,false);
            password = view_enter_register.findViewById(R.id.password);
            showPass = view_enter_register.findViewById(R.id.checkViewPass);
            builder_enter_register.setView(view_enter_register);
            EditText eMail = view_enter_register.findViewById(R.id.e_mail);
            EditText pass = view_enter_register.findViewById(R.id.password);
            builder_enter_register.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    signIn(eMail.getText().toString(), pass.getText().toString());
                    restartActivity(activityClientInfo);
                    Toast.makeText(activityClientInfo, "Идет связь с сервером", Toast.LENGTH_SHORT).show();
                }
            });

            showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked){
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    else {
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                }
            });
            AlertDialog dialog_enter_regular_customer = builder_enter_register.create();
            dialog_enter_regular_customer.show();
            dialog_enter_regular_customer.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            eMail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(TextUtils.isEmpty(s)){
                        dialog_enter_regular_customer.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    } else{
                        pass.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if(TextUtils.isEmpty(s)){
                                    dialog_enter_regular_customer.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                } else{
                                    dialog_enter_regular_customer.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                }
                            }
                        });
                    }
                }
            });
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

    public void restartActivity(Activity activity){
        activity.finish();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ActivityClientInfo.this, ActivityClientInfo.class);
                startActivity(intent);
            }
        },2000);

    }

    public void viewQrCode(){
        String getUserId = user.getUid();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(getUserId, BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
