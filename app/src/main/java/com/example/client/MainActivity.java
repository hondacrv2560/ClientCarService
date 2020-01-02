package com.example.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //создание подключения к БД
    private FirebaseAuth firebaseAuth;
    //создание подключения к авторизапции
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



}
