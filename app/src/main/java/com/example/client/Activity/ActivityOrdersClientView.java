package com.example.client.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.OrdersClientViewAdapter;
import com.example.client.Models.OrderViewClient;
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

import java.util.ArrayList;

public class ActivityOrdersClientView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrdersClientViewAdapter ordersClientViewAdapter;
    private ArrayList<OrderViewClient> orderViewClientList;
    private Activity activityOrdersClientView;
    private FirebaseAuth firebaseAuth;
    DatabaseReference dbOrders;
    Button btnOk;
    EditText userAuth;
    EditText passAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_client_view);

        activityOrdersClientView = ActivityOrdersClientView.this;
        recyclerView = findViewById(R.id.orderList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderViewClientList = new ArrayList<OrderViewClient>();
        ordersClientViewAdapter = new OrdersClientViewAdapter(this, orderViewClientList);
        recyclerView.setAdapter(ordersClientViewAdapter);
        dbOrders = FirebaseDatabase.getInstance().getReference("Orders");
        btnOk = findViewById(R.id.okBtnOrder);
        userAuth = findViewById(R.id.authUserOrder);
        passAuth = findViewById(R.id.authPassOrder);

        firebaseAuth = firebaseAuth.getInstance();
        // gjkextv Uid user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        try {
            Query query = dbOrders
                .orderByChild("UserId")
                .equalTo(user.getUid());
            query.addListenerForSingleValueEvent(valueEventListener);
            Toast.makeText(ActivityOrdersClientView.this, "signed in" + user.getUid(), Toast.LENGTH_SHORT).show();
            btnOk.setVisibility(View.INVISIBLE);
            userAuth.setVisibility(View.INVISIBLE);
            passAuth.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityOrdersClientView.this, "пожалуйста пройдите авторизацию", Toast.LENGTH_LONG).show();
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(userAuth.getText().toString(), passAuth.getText().toString());
                restartActivity(activityOrdersClientView);
            }
        });

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

    // вход зарегистрированного клиента
    public void signIn(String e_mail, String pass){
        firebaseAuth.signInWithEmailAndPassword(e_mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ActivityOrdersClientView.this, "Ok", Toast.LENGTH_SHORT).show();
                }else{
                    String TAG="test";
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(ActivityOrdersClientView.this, "Bad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void restartActivity(Activity activity){
        activity.recreate();
    }
}
