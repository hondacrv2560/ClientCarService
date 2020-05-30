package com.example.client.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.client.MainActivity;
import com.example.client.MapsActivity;
import com.example.client.R;

public class ActivityContacts extends AppCompatActivity {

    Context context;
    TextView kyivstar;
    TextView vodafone;
    TextView lifecel;
    TextView intertelecom;
    ImageView imgFacebook;
    ImageView imgViber;
    ImageView imgWhatsApp;
    ImageView imgTelegram;
    ImageView imgYouTube;
    public static String FACEBOOK_URL = "https://www.facebook.com/100052101661602";
    public static String FACEBOOK_PAGE_ID = "100052101661602";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        context=getApplicationContext();
        TextView adress = findViewById(R.id.adress);
        kyivstar = findViewById(R.id.kyivstar);
        vodafone = findViewById(R.id.vodafone);
        lifecel = findViewById(R.id.lifecel);
        intertelecom = findViewById(R.id.intertelecom);
        imgFacebook = findViewById(R.id.facebook);
        imgViber = findViewById(R.id.viber);
        imgWhatsApp = findViewById(R.id.whatsapp);
        imgTelegram = findViewById(R.id.telegram);
        imgYouTube = findViewById(R.id.youtube);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showMap = new Intent(ActivityContacts.this, MapsActivity.class);
                startActivity(showMap);
            }
        });

        imgViber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("viber://invite.viber.com/?g2=AQAEKUdVIalZD0uWmh5cIynWi2ncuVluAcrbD1nOhfNiw9eLL9OawigelleHfi6U"));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityContacts.this, "Нужное приложение не найдено", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAppInstalled()){
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(context);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Нужное приложение не найдено", Toast.LENGTH_SHORT).show();
                }
//                try{
//                    Intent intentFacebook = new Intent(Intent.ACTION_VIEW);
//                    intentFacebook.setData(Uri.parse("fb://page/100052101661602"));
//                    startActivity(intentFacebook);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(ActivityContacts.this, "Нужное приложение не найдено", Toast.LENGTH_SHORT).show();
//                }

            }
        });
        imgTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intentTelegram = new Intent(Intent.ACTION_VIEW);
                    intentTelegram.setPackage("org.telegram.messenger");
                    intentTelegram.setData(Uri.parse("https://t.me/akva_deteiling"));
                    startActivity(intentTelegram);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityContacts.this, "Нужное приложение не найдено", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imgWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intentWhatsApp = new Intent(Intent.ACTION_VIEW);
                    intentWhatsApp.setPackage("com.whatsapp");
                    intentWhatsApp.setData(Uri.parse("https://chat.whatsapp.com/FZqkpMRFfQS643peyZvOsN"));
                    startActivity(intentWhatsApp);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityContacts.this, "Нужное приложение не найдено", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imgYouTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("google.com"));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityContacts.this, "Нужное приложение не найдено", Toast.LENGTH_SHORT).show();
                }

            }
        });

        vodafone.setOnClickListener(v -> {
            Toast.makeText(ActivityContacts.this, "test", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+vodafone.getText().toString()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        kyivstar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+kyivstar.getText().toString()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        lifecel.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+lifecel.getText().toString()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        intertelecom.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+intertelecom.getText().toString()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
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

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.orca", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    public boolean isAppInstalled() {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
