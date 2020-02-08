package com.example.client.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.R;

public class ActivityContacts extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView adress = findViewById(R.id.adress);
        TextView kyivstar = findViewById(R.id.kyivstar);
        TextView vodafone = findViewById(R.id.vodafone);
        TextView intertelecom = findViewById(R.id.intertelecom);
    }

    
}
