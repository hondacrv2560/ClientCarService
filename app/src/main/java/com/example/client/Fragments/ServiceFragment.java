package com.example.client.Fragments;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    // получение ссылки на БД
    DatabaseReference myDbReference = database.getReference();
    // поключение к child Orders
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

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }
        public void setIdTitle(String string){idTitle.setText(string);}
    }

    private void fetch() {
        Query query = myDbReference.child("Service");

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
//                holder.setTxtTitle(service.getTitle_service());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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