package com.example.client.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.Fragments.OneDay;
import com.example.client.Models.Order;
import com.example.client.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leinardi.android.speeddial.SpeedDialView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    TextView btnCancel;
    TextView btnOk;
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
    private Order order;
    private String key;
    public List<Order> list;
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
    public String userId;

    Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userId = user.getUid();
        setContentView(R.layout.add_order);
        addDate = findViewById(R.id.dateOrder);
        addTime = findViewById(R.id.timeOrder);
        speedDialView = findViewById(R.id.speedDial);
        addComment = findViewById(R.id.addComment);
        govNumber = findViewById(R.id.govNumberCarClient);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
        list = new ArrayList<Order>();
        btnOk.setOnClickListener(view -> {
            orderRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataSnapshot.getChildren();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Order eventOrder = snapshot.getValue(Order.class);

                        if(eventOrder.startTimeYear==startOrderYear&&eventOrder.startTimeMonth==startOrderMonth&&eventOrder.startDayOfMonth==startOrderMonth&&
                                eventOrder.startTimeHour==startOrderHour&&eventOrder.startTimeMinute==startOrderMinute){
                            list.add(eventOrder);
                        }
                    }
                       if(list.size()>3) {
                           Toast.makeText(ActivityAOR3Phases.this, "Can't add order", Toast.LENGTH_SHORT).show();
                       }else{

                           Toast.makeText(ActivityAOR3Phases.this, "Can add order", Toast.LENGTH_SHORT).show();
                       }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker();
            }
        });

        setUpperCase();
    }

    public void timePicker (){
        Calendar calendar = Calendar.getInstance();
        startOrderHour = calendar.get(Calendar.HOUR_OF_DAY);
        startOrderMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityAOR3Phases.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ActivityAOR3Phases.this.startOrderHour=hourOfDay;
                        ActivityAOR3Phases.this.startOrderMinute=minute;
                        addTime.setText(String.format ("%02d:%02d", hourOfDay, minute));
                        String hour = hourOfDay + ":" + minute;
                        Toast.makeText(ActivityAOR3Phases.this, hour, Toast.LENGTH_SHORT).show();
                    }
                }, startOrderHour, startOrderMinute, true);
        timePickerDialog.show();
    }

    // создание DatePickerDialog
    public void datePicker (){
        Calendar calendar = Calendar.getInstance();
        startOrderDay = calendar.get (Calendar.DAY_OF_MONTH);
        startOrderMonth= calendar.get (Calendar.MONTH)+1;
        startOrderYear = calendar.get (Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(ActivityAOR3Phases.this, AlertDialog.THEME_TRADITIONAL){
            @Override
            public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
                ActivityAOR3Phases.this.startOrderYear=year;
                ActivityAOR3Phases.this.startOrderMonth= ++month;
                ActivityAOR3Phases.this.startOrderDay=dayOfMonth;
            }
        };
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Записаться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String date= startOrderDay+"/"+ startOrderMonth+"/"+startOrderYear;
                Toast.makeText(ActivityAOR3Phases.this, date, Toast.LENGTH_SHORT).show();
                addDate.setText(String.format("%02d-%02d-%d", startOrderDay, startOrderMonth, startOrderYear));
            }
        });
        datePickerDialog.show();
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
