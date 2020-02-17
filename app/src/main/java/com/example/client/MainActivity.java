package com.example.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.client.Activity.ActivityContacts;
import com.example.client.Activity.RegularClientActivity;
import com.example.client.Classes.OneDayActivity;
import com.example.client.Classes.SevenDaysActivity;
import com.example.client.Classes.ThreeDaysActivity;
import com.example.client.Classes.ToDayActivity;
import com.example.client.Fragments.OneDay;
import com.example.client.Fragments.SevenDays;
import com.example.client.Fragments.ThreeDays;
import com.example.client.Fragments.ToDay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //создание подключения к БД
    private FirebaseAuth firebaseAuth;
    //создание подключения к авторизапции
    private FirebaseAuth.AuthStateListener authStateListener;
    public List<DataSnapshot> list;
    private Fragment serviceFragment = null;
    private TextView password;
    private CheckBox showPass;
    private String viewMonth;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    AlertDialog.Builder builder_regular_customer;
    AlertDialog.Builder builder_enter_register;
    LayoutInflater inflater_regular_customer;
    LayoutInflater inflater_enter_register;
public String str;
    // подключение к БД
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Orders
    DatabaseReference orderRef = myDbReference.child("Orders");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFireBase();
//проверка на авторизацию, если клиент в приложении авторизирован
        firebaseAuth = firebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                String TAG="test_onAuthStateChanged";
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(MainActivity.this, "signed in", Toast.LENGTH_SHORT).show();
                    MenuItem regular_customer = findViewById(R.id.regular_customer);
                    regular_customer.setVisible(true);
                    Log.d(TAG,"onAuthStateChanged: signed in" + user.getUid());

                }else{
                    Log.d(TAG,"onAuthStateChanged: signed out");
                }
            }
        };
        // gjkextv Uid user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            Toast.makeText(MainActivity.this, "signed in" + user.getUid(), Toast.LENGTH_SHORT).show();
        }

        // блокировка текущего потока UI, для корректной загрузки фрагмента с полученными данными из БД
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentManager = getSupportFragmentManager();
                ToDay toDay = new ToDayActivity();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fr,toDay);
                transaction.commit();
            }
        },500);

        //отображение месяца в ActionBar
        Calendar calendar=Calendar.getInstance();
//        String month = new SimpleDateFormat("MMMM", new Locale("ru")).format(calendar.getTime());
        String[] monthNames = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };
        String month = monthNames[calendar.get(Calendar.MONTH)];
        getSupportActionBar().setTitle(month);
    }

    public void getDataFireBase() {
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<DataSnapshot>();
                dataSnapshot.getChildren();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    //        orderRef.addListenerForSingleValueEvent(listener);
    }

    // вход зарегистрированного клиента
    public void signIn(String e_mail, String pass){
        firebaseAuth.signInWithEmailAndPassword(e_mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                }else{
                    String TAG="test";
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Bad", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    // регистрация нового клиента
    public void registartion (String e_mail, String pass){
        firebaseAuth.createUserWithEmailAndPassword(e_mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Reg ok", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Reg Bad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            //регистрация нового клиента
            case R.id.regular_customer:

                builder_regular_customer = new AlertDialog.Builder(MainActivity.this);
                builder_regular_customer.setTitle("Регистрация нового клиента");
                inflater_regular_customer = MainActivity.this.getLayoutInflater();
                final View view_regular_customer = inflater_regular_customer.inflate(R.layout.activity_registartion,null,false);
                password = view_regular_customer.findViewById(R.id.password);
                showPass = view_regular_customer.findViewById(R.id.checkViewPass);
                builder_regular_customer.setView(view_regular_customer);
                builder_regular_customer.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText eMail = view_regular_customer.findViewById(R.id.e_mail);
                        EditText pass = view_regular_customer.findViewById(R.id.password);
                        registartion(eMail.getText().toString(), pass.getText().toString());
                        Intent intentRegularClient = new Intent(MainActivity.this, RegularClientActivity.class);
                        startActivity(intentRegularClient);
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
                AlertDialog dialog_regular_customer = builder_regular_customer.create();
                dialog_regular_customer.show();
                break;
                // вход зарегистрированного клиента
            case R.id.enter_register:
                builder_enter_register = new AlertDialog.Builder(MainActivity.this);
                builder_enter_register.setTitle("Вход зарегистрированного клиента");
                inflater_enter_register = MainActivity.this.getLayoutInflater();
                final View view_enter_register = inflater_enter_register.inflate(R.layout.activity_enter_regular_client,null,false);
                password = view_enter_register.findViewById(R.id.password);
                showPass = view_enter_register.findViewById(R.id.checkViewPass);
                builder_enter_register.setView(view_enter_register);
                builder_enter_register.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText eMail = view_enter_register.findViewById(R.id.e_mail);
                        EditText pass = view_enter_register.findViewById(R.id.password);
                        signIn(eMail.getText().toString(), pass.getText().toString());
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
                break;
            case R.id.mapView:
                Intent showMap = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(showMap);
                break;
            case R.id.contacts:
                Intent showContact = new Intent(MainActivity.this, ActivityContacts.class);
                startActivity(showContact);
//            case R.id.carWashing_3Phases:
//                str = "CarWashing_3Phases";
//                break;
//            case R.id.carWashing:
//                str = "CarWashing";
//                break;
//            case R.id.chemicalCleaningSalon:
//                str = "ChemicalCleaningSalon";
//                break;
//            case R.id.nanoCeramics:
//                str = "NanoCeramics";
//                break;
//            case R.id.polishing:
//                str = "Polishing";
//                break;
//            case R.id.protectiveFilm:
//                str = "ProtectiveFilm";
//                break;
//            case R.id.repairWindshield:
//                str = "RepairWindshield";
//                break;
//            case R.id.salonProtection:
//                str = "SalonProtection";
//                break;
//            case R.id.toning:
//                str = "Toning";
//                break;
//            case R.id.tireFitting:
//                str = "TireFitting";
//                break;
            case R.id.day_1:
                fragmentManager=getSupportFragmentManager();
                OneDay oneDay = new OneDayActivity();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.fr, oneDay);
                transaction.commit();
                break;
            case R.id.days_3:
                fragmentManager=getSupportFragmentManager();
                ThreeDays threeDays=new ThreeDaysActivity();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.fr,threeDays);
                transaction.commit();
                break;
            case R.id.week:
                fragmentManager=getSupportFragmentManager();
                SevenDays sevenDays=new SevenDaysActivity();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.fr,sevenDays);
                transaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
