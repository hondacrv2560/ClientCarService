package com.example.client.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.MainActivity;
import com.example.client.Models.Client;
import com.example.client.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class RegularClientActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private DatabaseReference myDbReference;
    private String key;
    FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    EditText nameSurnameNewClient;
    MaskedEditText numPhoneNewClient;
    EditText govNumberCarNewClient;
    EditText manufacturerCarNewClient;
    EditText modelCarNewClient;

    String strNameSurnameNewClient;
    String strGovNumberCarNewClient;
    String strManufacturerCarNewClient;
    String strModelCarNewClient;

    TextView addNewClient;

    TextView setDataBirthday;
    LinearLayout mainLr;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_client);
//        firebaseAuth=FirebaseAuth.getInstance();
        myDbReference = FirebaseDatabase.getInstance().getReference();

        nameSurnameNewClient = findViewById(R.id.name_surname_new_client);
        numPhoneNewClient = findViewById(R.id.num_phone_new_client);
        govNumberCarNewClient = findViewById(R.id.gov_number_car_new_client);
        manufacturerCarNewClient = findViewById(R.id.manufacturer_car_new_client);
        modelCarNewClient = findViewById(R.id.model_car_new_client);
        setDataBirthday=findViewById(R.id.birthday_new_client);
        addNewClient = findViewById(R.id.add_new_client);
        mainLr=findViewById(R.id.mainLr);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Calendar calendar=Calendar.getInstance();
        day=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);

        addNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = new Client(user.getUid(), nameSurnameNewClient.getText().toString(),
                        numPhoneNewClient.getText().toString(),
                        govNumberCarNewClient.getText().toString(),
                        manufacturerCarNewClient.getText().toString(),
                        modelCarNewClient.getText().toString(),
                        setDataBirthday.getText().toString());
                myDbReference = database.getReference("Clients");
                key = myDbReference.push().getKey();
                myDbReference.child(Objects.requireNonNull(key)).setValue(client);
                Intent intentRegularClient = new Intent(RegularClientActivity.this, MainActivity.class);
                startActivity(intentRegularClient);
            }
        });
        setUpperCase();
    }

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

    public void onClick(View arg0) {
        DatePickerDialog datePickerDialog=new DatePickerDialog(RegularClientActivity.this, AlertDialog.THEME_TRADITIONAL,
                null,year, month,day){
            @Override
            public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
                RegularClientActivity.this.year=year;
                RegularClientActivity.this.month= ++month;
                RegularClientActivity.this.day=dayOfMonth;
            }
        };
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String date=day+"/"+ month+"/"+year;
                Toast.makeText(RegularClientActivity.this, date, Toast.LENGTH_SHORT).show();
                setDataBirthday.setText(date);
            }
        });
        datePickerDialog.show();
    }

    public void setUpperCase(){
        nameSurnameNewClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
               strNameSurnameNewClient = s.toString();
                if(!strNameSurnameNewClient.equals(strNameSurnameNewClient.toUpperCase())){
                    strNameSurnameNewClient = strNameSurnameNewClient.toUpperCase();
                    nameSurnameNewClient.setText(strNameSurnameNewClient);
                }
                nameSurnameNewClient.setSelection(nameSurnameNewClient.getText().length());
            }
        });
        govNumberCarNewClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                strGovNumberCarNewClient = s.toString();
                if(!strGovNumberCarNewClient.equals(strGovNumberCarNewClient.toUpperCase())){
                    strGovNumberCarNewClient = strGovNumberCarNewClient.toUpperCase();
                    govNumberCarNewClient.setText(strGovNumberCarNewClient);
                }
                govNumberCarNewClient.setSelection(govNumberCarNewClient.getText().length());
            }
        });
        manufacturerCarNewClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                strManufacturerCarNewClient = s.toString();
                if(!strManufacturerCarNewClient.equals(strManufacturerCarNewClient.toUpperCase())){
                    strManufacturerCarNewClient = strManufacturerCarNewClient.toUpperCase();
                    manufacturerCarNewClient.setText(strManufacturerCarNewClient);
                }
                manufacturerCarNewClient.setSelection(manufacturerCarNewClient.getText().length());
            }
        });
        modelCarNewClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                strModelCarNewClient = s.toString();
                if(!strModelCarNewClient.equals(strModelCarNewClient.toUpperCase())){
                    strModelCarNewClient = strModelCarNewClient.toUpperCase();
                    modelCarNewClient.setText(strModelCarNewClient);
                }
                modelCarNewClient.setSelection(modelCarNewClient.getText().length());
            }
        });
    }
}
