package com.example.client.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.Models.Order;
import com.example.client.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leinardi.android.speeddial.SpeedDialView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

public class ActivityAOR3Phases extends AppCompatActivity {

    TextView addDate;
    TextView addTime;
    SpeedDialView speedDialView;
    EditText govNumber;
    EditText addComment;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myDbReferenceOrder;
    private DatabaseReference myDbReferenceEventOrder;
    FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
    // подключение к БД
    FirebaseDatabase dbEvent = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = dbEvent.getReference();
    // поключение к child Orders
    DatabaseReference orderRef = myDbReference.child("Orders");
    public List<Order> list = new ArrayList<Order>();
    int startOrderDay;
    int startOrderMonth;
    int startOrderYear;
    int startOrderHour =-1;
    int startOrderMinute =-1;
    Date currentDate;
    DateFormat dateFormat;
    DateFormat timeFormat;
    public String dateText;
    public String timeText;

    Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_order);
        addDate = findViewById(R.id.dateOrder);
        addTime = findViewById(R.id.timeOrder);
        speedDialView = findViewById(R.id.speedDial);
        addComment = findViewById(R.id.addComment);
        govNumber = findViewById(R.id.govNumberCarClient);
    }

    public void getCurrentDateTime(){
        // Текущее время
        currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
// Форматирование времени как "часы:минуты:секунды"
        timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        dateText = dateFormat.format(currentDate);
        timeText = timeFormat.format(currentDate);
    }

    public void setUpperCase(){
        govNumber.addTextChangedListener(new TextWatcher() {
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
                    govNumber.setText(strGovNumber);
                }
                govNumber.setSelection(govNumber.getText().length());
            }
        });
    }
}
