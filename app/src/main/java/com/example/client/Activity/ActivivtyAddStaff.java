package com.example.client.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.MainActivity;
import com.example.client.Models.Staff;
import com.example.client.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ActivivtyAddStaff extends AppCompatActivity {
    private String key;
    private DatabaseReference myDbReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText functionStaff;
    EditText nameStaff;
    EditText surnameStaff;
    MaskedEditText numphoneStaff;
    EditText seriesPassportStaff;
    EditText numberPassportStaff;
    EditText passportIssuingAuthorityStaff;
    EditText dateIssuePassportStaff;
    TextView dateBirthStaff;
    TextView addStaff;

    String strFunctionStaff;
    String strNameStaff;
    String strSurnameStaff;
    String strSeriesPassportStaff;
    String strPassportIssuingAuthorityStaff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activivty_add_staff);

        functionStaff = findViewById(R.id.functionStaff);
        nameStaff = findViewById(R.id.nameStaff);
        surnameStaff = findViewById(R.id.surnameStaff);
        numphoneStaff = findViewById(R.id.numphoneStaff);
        seriesPassportStaff = findViewById(R.id.seriesPassportStaff);
        numberPassportStaff = findViewById(R.id.numberPassportStaff);
        passportIssuingAuthorityStaff = findViewById(R.id.passportIssuingAuthorityStaff);
        dateIssuePassportStaff = findViewById(R.id.dateIssuePassportStaff);
        dateBirthStaff = findViewById(R.id.dateBirthStaff);
        addStaff = findViewById(R.id.addStaff);

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Staff addStaff = new Staff(functionStaff.getText().toString(),
                        nameStaff.getText().toString(),
                        surnameStaff.getText().toString(),
                        numphoneStaff.getText().toString(),
                        seriesPassportStaff.getText().toString(),
                        numberPassportStaff.getText().toString(),
                        passportIssuingAuthorityStaff.getText().toString(),
                        dateIssuePassportStaff.getText().toString(),
                        dateBirthStaff.getText().toString());
                myDbReference = database.getReference("Staff");
                key = myDbReference.push().getKey();
                myDbReference.child(Objects.requireNonNull(key)).setValue(addStaff);
                Intent intentRegularClient = new Intent(ActivivtyAddStaff.this, MainActivity.class);
                startActivity(intentRegularClient);
                Toast.makeText(ActivivtyAddStaff.this, key, Toast.LENGTH_SHORT).show();
            }
        });
        setUpperCase();
    }

    public void setUpperCase() {
        functionStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                strFunctionStaff = s.toString();
                if (!strFunctionStaff.equals(strFunctionStaff.toUpperCase())) {
                    strFunctionStaff = strFunctionStaff.toUpperCase();
                    functionStaff.setText(strFunctionStaff);
                }
                functionStaff.setSelection(functionStaff.getText().length());
            }
        });

        nameStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                strNameStaff = s.toString();
                if (!strNameStaff.equals(strNameStaff.toUpperCase())) {
                    strNameStaff = strNameStaff.toUpperCase();
                    nameStaff.setText(strNameStaff);
                }
                nameStaff.setSelection(nameStaff.getText().length());
            }
        });

        surnameStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                strSurnameStaff = s.toString();
                if (!strSurnameStaff.equals(strSurnameStaff.toUpperCase())) {
                    strSurnameStaff = strSurnameStaff.toUpperCase();
                    surnameStaff.setText(strSurnameStaff);
                }
                surnameStaff.setSelection(surnameStaff.getText().length());
            }
        });

        seriesPassportStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                strSeriesPassportStaff = s.toString();
                if (!strSeriesPassportStaff.equals(strSeriesPassportStaff.toUpperCase())) {
                    strSeriesPassportStaff = strSeriesPassportStaff.toUpperCase();
                    seriesPassportStaff.setText(strSeriesPassportStaff);
                }
                seriesPassportStaff.setSelection(seriesPassportStaff.getText().length());
            }
        });

        passportIssuingAuthorityStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                strPassportIssuingAuthorityStaff = s.toString();
                if (!strPassportIssuingAuthorityStaff.equals(strPassportIssuingAuthorityStaff.toUpperCase())) {
                    strPassportIssuingAuthorityStaff = strPassportIssuingAuthorityStaff.toUpperCase();
                    passportIssuingAuthorityStaff.setText(strPassportIssuingAuthorityStaff);
                }
                passportIssuingAuthorityStaff.setSelection(passportIssuingAuthorityStaff.getText().length());
            }
        });
    }
}
