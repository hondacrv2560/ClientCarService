package com.example.client.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.MainActivity;
import com.example.client.Models.CarWashing_3Phases;
import com.example.client.Models.ServiceClient;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ServiceClientActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Service
    DatabaseReference serviceRef = myDbReference.child("ServiceClient");

    public TextView txtView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);
        txtView=findViewById(R.id.txtInfo);

        recyclerView = findViewById(R.id.ServiceList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtIdService;
        public TextView txtTitlService;
<<<<<<< Updated upstream
        public ImageView imgPath;
=======
        public ImageView imgService;
>>>>>>> Stashed changes


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtIdService = itemView.findViewById(R.id.id_title);
            txtTitlService = itemView.findViewById(R.id.list_title);
<<<<<<< Updated upstream
            imgPath = itemView.findViewById(R.id.photoService);
=======
            imgService = itemView.findViewById(R.id.photoService);


>>>>>>> Stashed changes
        }

        public void setTxtId(String string) {
            txtIdService.setText(string);
        }
        public void setTxtTitle(String string) {
            txtTitlService.setText(string);
        }
<<<<<<< Updated upstream
        public void setImgService(String string) {Picasso.get().load(string).into(imgPath);}
=======
        public void setImgService(int redId) {imgService.setImageResource(redId);}
>>>>>>> Stashed changes
    }

    private void fetch() {
        Query query = serviceRef;

        FirebaseRecyclerOptions<ServiceClient> options =
                new FirebaseRecyclerOptions.Builder<ServiceClient>()
                        .setQuery(query, new SnapshotParser<ServiceClient>() {
                            @NonNull
                            @Override
                            public ServiceClient parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new ServiceClient(snapshot.child("idService").getValue().toString(),
                                        snapshot.child("titleService").getValue().toString(),
                                        snapshot.child("imagePath").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<ServiceClient, ServiceClientActivity.ViewHolder>(options) {

            @Override
            public ServiceClientActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.service_list, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ServiceClientActivity.ViewHolder holder, final int position, ServiceClient serviceClient) {

                holder.setTxtId(serviceClient.getIdService());
                holder.setTxtTitle(serviceClient.getTitle_service());
<<<<<<< Updated upstream
                holder.setImgService(serviceClient.getImagePath());

=======
                holder.setImgService(serviceClient.getImgId());
>>>>>>> Stashed changes

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id=Integer.valueOf(position);
                        switch (id){
                            case 0:
                                Intent carWashing_3PhasesFragment = new Intent(ServiceClientActivity.this, CarWashing_3PhasesActivity.class);
                                startActivity(carWashing_3PhasesFragment);
                                break;

                            case 1:

                                break;

                            case 2:
                                Intent repairWindshield = new Intent(ServiceClientActivity.this, RepairWindshieldActivity.class);
                                startActivity(repairWindshield);
                                break;

                            case 3:
                                Intent polishing = new Intent(ServiceClientActivity.this, PolishingActivity.class);
                                startActivity(polishing);
                                break;

                            case 4:
                                Intent nanoCeramics = new Intent(ServiceClientActivity.this, NanoCeramicsActivity.class);
                                startActivity(nanoCeramics);
                                break;

                            case 5:
                                Intent protectiveFilm = new Intent(ServiceClientActivity.this, ProtectiveFilmActivity.class);
                                startActivity(protectiveFilm);
                                break;

                            case 6:
                                Intent chemicalCleaningSalon = new Intent(ServiceClientActivity.this, ChemicalCleaningSalonActivivy.class);
                                startActivity(chemicalCleaningSalon);
                                break;

                            case 7:
                                Intent salonProtection = new Intent(ServiceClientActivity.this, SalonProtectionActivity.class);
                                startActivity(salonProtection);
                                break;

                            case 8:

                                break;

                            case 9:
                                Intent toning = new Intent(ServiceClientActivity.this, ToningActivity.class);
                                startActivity(toning);
                                break;
                        }
                        Toast.makeText(ServiceClientActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service_client,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_next:
                Intent mainActivivty = new Intent(ServiceClientActivity.this, MainActivity.class);
                startActivity(mainActivivty);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
