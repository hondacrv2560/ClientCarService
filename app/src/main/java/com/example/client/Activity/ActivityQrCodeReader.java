package com.example.client.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.client.R;
import com.google.zxing.Result;

public class ActivityQrCodeReader extends AppCompatActivity {
    private CodeScanner codeScanner;
    String qrCode;
    Button btnQr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_reader);
        String txtTitleService = getIntent().getStringExtra("titleService");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        final Activity activity = this;

        btnQr = findViewById(R.id.btnQrCode);

        CodeScannerView scannerView = findViewById(R.id.scanner_viewQr);
        codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        qrCode = result.getText();
                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });

        btnQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (txtTitleService){
                    case "CW_3Phases":
                        Intent cw_3Phases = new Intent(ActivityQrCodeReader.this, ActivityFullOrderCW_3Phases.class);
                        cw_3Phases.putExtra("qrCode", "user unregister");
//                        cw_3Phases.putExtra("qrCode", qrCode);
                        startActivity(cw_3Phases);
                        finish();
                        break;
                    case "CW_Standard":
                        Intent cw_Standard = new Intent(ActivityQrCodeReader.this, ActivityFullOrderCW_Standart.class);
                        cw_Standard.putExtra("qrCode", "user unregister");
//                        cw_Standard.putExtra("qrCode", qrCode);
                        startActivity(cw_Standard);
                        finish();
                        break;
                }

            }
        });
    }
}
