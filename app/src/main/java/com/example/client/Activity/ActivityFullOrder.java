package com.example.client.Activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Classes.FullOrderViewHolder;
import com.example.client.Interface.IItemClickListener;
import com.example.client.Models.FullOrder;
import com.example.client.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class ActivityFullOrder extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FullOrder> fullOrderList = new ArrayList<>();
    FirebaseRecyclerAdapter<FullOrder, FullOrderViewHolder> adapter;

    SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_full_order);

        recyclerView = findViewById(R.id.recycler_Expand);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveData();
        setData();
    }

    private void setData() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Items");
        FirebaseRecyclerOptions<FullOrder> options = new FirebaseRecyclerOptions.Builder<FullOrder>()
                .setQuery(query, FullOrder.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<FullOrder, FullOrderViewHolder>(options) {
            @Override
            public int getItemViewType(int position) {
                if(fullOrderList.get(position).isExpandable()){
                    return 1;
                }else {
                    return 0;
                }
            }

            @Override
            protected void onBindViewHolder(@NonNull FullOrderViewHolder fullOrderViewHolder, int position, @NonNull FullOrder fullOrder) {
                switch (fullOrderViewHolder.getItemViewType()){
                    case 0:{
                        FullOrderViewHolder viewHolder=(FullOrderViewHolder)fullOrderViewHolder;
                        viewHolder.setIsRecyclable(false);
                        viewHolder.txt_item_text.setText(fullOrder.getText());
                        viewHolder.setiItemClickListener(new IItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(ActivityFullOrder.this, "without child"+fullOrderList.get(position).getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                    case 1:{
                        FullOrderViewHolder viewHolder=(FullOrderViewHolder)fullOrderViewHolder;
                        viewHolder.setIsRecyclable(false);
                        viewHolder.txt_item_text.setText(fullOrder.getText());

                        viewHolder.expandableLinearLayout.setInRecyclerView(true);
                        viewHolder.expandableLinearLayout.setExpanded(sparseBooleanArray.get(position));

                        viewHolder.expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                            @Override
                            public void onPreOpen() {
                                changeRotate(viewHolder.relativeLayout, 0f, 180f).start();
                                sparseBooleanArray.put(position,true);

                            }

                            @Override
                            public void onPreClose() {
                                changeRotate(viewHolder.relativeLayout, 180f, 0f).start();
                                sparseBooleanArray.put(position,false);
                            }
//
//                            @Override
//                            public void onOpened() {
//                                super.onOpened();
//                            }
//
//                            @Override
//                            public void onClosed() {
//                                super.onClosed();
//                            }
                        });
                        viewHolder.relativeLayout.setRotation(sparseBooleanArray.get(position)?180f:0f);
                        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHolder.expandableLinearLayout.toggle();

                            }
                        });
                        viewHolder.txt_child_text.setText(fullOrder.getText());
                        viewHolder.txt_child_text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ActivityFullOrder.this, ""+viewHolder.txt_child_text.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        viewHolder.setiItemClickListener(new IItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(ActivityFullOrder.this, ""+fullOrder.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;

                }
            }

            @NonNull
            @Override
            public FullOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                if(viewType ==0){
//                    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_full_order, parent, false);
//                    return new FullOrderViewHolder(itemView, viewType==1);
//                }else{
//                    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_full_order, parent, false);
//                    return new FullOrderViewHolder(itemView, viewType==1);
//                }

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_full_order, parent, false);
                return new FullOrderViewHolder(itemView, viewType==1);
            }
        };

        sparseBooleanArray.clear();
        for (int i=0;i<fullOrderList.size();i++){
            sparseBooleanArray.append(i,false);
        }
        recyclerView.setAdapter(adapter);
    }

    private ObjectAnimator changeRotate(RelativeLayout relativeLayout, float from, float to) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(relativeLayout, "rotation", from, to);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return objectAnimator;
    }

    private void retrieveData() {
        fullOrderList.clear();
        DatabaseReference dbFullOrder = FirebaseDatabase.getInstance().getReference()
                .child("Items");
        dbFullOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    FullOrder fullOrder = ds.getValue(FullOrder.class);
                    fullOrderList.add(fullOrder);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR", ""+databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        if(adapter!=null)
            adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if(adapter!=null)
            adapter.stopListening();
        super.onStop();
    }
}
