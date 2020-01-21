package com.example.client.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Activity.CarWashing_3PhasesActivity;
import com.example.client.Activity.PolishingActivity;
import com.example.client.Activity.RepairWindshieldActivity;
import com.example.client.Activity.ToningActivity;
import com.example.client.Models.Service;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ServiceFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    public  TextView txtView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Service
    DatabaseReference serviceRef = myDbReference.child("Service");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View serviceFragment = inflater.inflate(R.layout.service_view, container,false);

        recyclerView = serviceFragment.findViewById(R.id.service_list);
        txtView = serviceFragment.findViewById(R.id.txt1);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

//        Toast.makeText(getActivity(), "Fragment Sevice", Toast.LENGTH_SHORT).show();
        return serviceFragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;
        public TextView idTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            idTitle=itemView.findViewById(R.id.id_title);
            txtTitle = itemView.findViewById(R.id.list_title);
        }

        public void setTxtTitle(String string){txtTitle.setText(string);}
        public void setIdTitle(String string){idTitle.setText(string);}
    }

    private void fetch() {
        Query query = serviceRef;

        FirebaseRecyclerOptions<Service> options =
                new FirebaseRecyclerOptions.Builder<Service>()
                        .setQuery(query, new SnapshotParser<Service>() {
                            @NonNull
                            @Override
                            public Service parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Service(snapshot.child("idService").getValue().toString(),
                                        snapshot.child("titleService").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Service, ViewHolder>(options) {


            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.service_list, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Service service) {
                holder.setIdTitle(service.getIdService());
                holder.setTxtTitle(service.getTitle_service());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id=Integer.valueOf(position);
                        switch (id){
                            case 0:
                                Intent carWashing_3PhasesFragment = new Intent(getContext(), CarWashing_3PhasesActivity.class);
                                startActivity(carWashing_3PhasesFragment);
                                break;

                            case 1:

                                break;

                            case 2:
                                Intent repairWindshield = new Intent(getContext(), RepairWindshieldActivity.class);
                                startActivity(repairWindshield);
                                break;
                            case 3:
                                Intent polishing = new Intent(getContext(), PolishingActivity.class);
                                startActivity(polishing);
                                break;
                            case 9:
                                Intent toning = new Intent(getContext(), ToningActivity.class);
                                startActivity(toning);
                                break;
                        }



                        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }
}
