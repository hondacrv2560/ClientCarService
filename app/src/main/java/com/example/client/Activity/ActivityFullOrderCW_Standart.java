package com.example.client.Activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.example.client.Models.FullOrders;
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
import java.util.Objects;

public class ActivityFullOrderCW_Standart extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FullOrder> fullOrderList = new ArrayList<>();
    FirebaseRecyclerAdapter<FullOrder, FullOrderViewHolder> adapter;
    public Button buttonOrder;
    public EditText idorder;
    public EditText idclient;
    List<FullOrders> ordersList = new ArrayList<>();
    FullOrders fullOrders;
    private DatabaseReference myDbReferenceOrder;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String key;
    private String str = null;
    public FullOrderViewHolder viewHolder;
    SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_full_order);
        buttonOrder = findViewById(R.id.buttonOrders);
        idclient = findViewById(R.id.id_Client);
        idorder = findViewById(R.id.id_Order);
        recyclerView = findViewById(R.id.recycler_Expand);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveData();
        setData();
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDbReferenceOrder = database.getReference("FullOrders");
                key = myDbReferenceOrder.push().getKey();
                myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(ordersList);
                ordersList.clear();
                finish();
            }
        });
    }

    private void setData() {
        Query query = FirebaseDatabase.getInstance().getReference().child("OrderCWStandart");
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
//                        FullOrderViewHolder viewHolder=(FullOrderViewHolder)fullOrderViewHolder;
//                        viewHolder.setIsRecyclable(false);
//                        viewHolder.txt_item_text.setText(fullOrder.getTitleService());
//                        viewHolder.setiItemClickListener(new IItemClickListener() {
//                            @Override
//                            public void onClick(View view, int position) {
//                                Toast.makeText(ActivityFullOrder.this, "without child"+fullOrderList.get(position).getText(), Toast.LENGTH_SHORT).show();
//                            }
//                        });

                        FullOrderViewHolder viewHolder=(FullOrderViewHolder)fullOrderViewHolder;
                        viewHolder.setIsRecyclable(false);
                        viewHolder.txt_titleService.setText(fullOrder.getTitleService());

                        viewHolder.setiItemClickListener(new IItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(ActivityFullOrderCW_Standart.this, "without child"+fullOrderList.get(position).getTitleService(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                    case 1:{
                        FullOrderViewHolder viewHolder=(FullOrderViewHolder)fullOrderViewHolder;
                        viewHolder.setIsRecyclable(false);
                        viewHolder.txt_titleService.setText(fullOrder.getTitleService());
                        viewHolder.txt_idService.setText(fullOrder.getIdService()+"");
                        viewHolder.txt_idService.setVisibility(View.GONE);

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
                        });
                        viewHolder.relativeLayout.setRotation(sparseBooleanArray.get(position)?180f:0f);
                        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHolder.expandableLinearLayout.toggle();

                            }
                        });

                        viewHolder.txt_price_sedan.setText(fullOrder.getPrice_sedan()+"");
                        viewHolder.txt_price_premium.setText(fullOrder.getPrice_premium()+"");
                        viewHolder.txt_price_business.setText(fullOrder.getPrice_business()+"");
                        viewHolder.txt_price_SUV.setText(fullOrder.getPrice_SUV()+"");
                        viewHolder.txt_price_BigSUV.setText(fullOrder.getPrice_BigSUV()+"");

                        viewHolder.txt_cat_sedan.setText(fullOrder.getCat_sedan());
                        viewHolder.txt_cat_premium.setText(fullOrder.getCat_premium());
                        viewHolder.txt_cat_business.setText(fullOrder.getCat_business());
                        viewHolder.txt_cat_SUV.setText(fullOrder.getCat_SUV());
                        viewHolder.txt_cat_BigSUV.setText(fullOrder.getCat_BigSUV());

                        viewHolder.checkBoxPremium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_cat_premium.getText().toString(),Integer.parseInt(viewHolder.txt_price_premium.getText().toString()), idclient.getText().toString(), idorder.getText().toString());
                                    ordersList.add(fullOrders);
                                    viewHolder.checkBoxSedan.setEnabled(false);
                                    viewHolder.checkBoxBigSUV.setEnabled(false);
                                    viewHolder.checkBoxSUV.setEnabled(false);
                                    viewHolder.checkBoxBusiness.setEnabled(false);
                                } else {
                                    ordersList.remove(ordersList.size() - 1);
                                    viewHolder.checkBoxSedan.setEnabled(true);
                                    viewHolder.checkBoxBigSUV.setEnabled(true);
                                    viewHolder.checkBoxSUV.setEnabled(true);
                                    viewHolder.checkBoxBusiness.setEnabled(true);
                                }
                            }
                        });
                        viewHolder.checkBoxBusiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_cat_business.getText().toString(),Integer.parseInt(viewHolder.txt_price_premium.getText().toString()), idclient.getText().toString(), idorder.getText().toString());
                                    ordersList.add(fullOrders);
                                    viewHolder.checkBoxSedan.setEnabled(false);
                                    viewHolder.checkBoxBigSUV.setEnabled(false);
                                    viewHolder.checkBoxSUV.setEnabled(false);
                                    viewHolder.checkBoxPremium.setEnabled(false);
                                } else {
                                    ordersList.remove(ordersList.size() - 1);
                                    viewHolder.checkBoxSedan.setEnabled(true);
                                    viewHolder.checkBoxBigSUV.setEnabled(true);
                                    viewHolder.checkBoxSUV.setEnabled(true);
                                    viewHolder.checkBoxPremium.setEnabled(true);
                                }
                            }
                        });
                        viewHolder.checkBoxSUV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_cat_SUV.getText().toString(),Integer.parseInt(viewHolder.txt_price_SUV.getText().toString()), idclient.getText().toString(), idorder.getText().toString());
                                    ordersList.add(fullOrders);
                                    viewHolder.checkBoxSedan.setEnabled(false);
                                    viewHolder.checkBoxBigSUV.setEnabled(false);
                                    viewHolder.checkBoxBusiness.setEnabled(false);
                                    viewHolder.checkBoxPremium.setEnabled(false);
                                } else {
                                    ordersList.remove(ordersList.size() - 1);
                                    viewHolder.checkBoxSedan.setEnabled(true);
                                    viewHolder.checkBoxBigSUV.setEnabled(true);
                                    viewHolder.checkBoxBusiness.setEnabled(true);
                                    viewHolder.checkBoxPremium.setEnabled(true);
                                }
                            }
                        });
                        viewHolder.checkBoxBigSUV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_cat_BigSUV.getText().toString(),Integer.parseInt(viewHolder.txt_price_BigSUV.getText().toString()), idclient.getText().toString(), idorder.getText().toString());
                                    ordersList.add(fullOrders);
                                    viewHolder.checkBoxSedan.setEnabled(false);
                                    viewHolder.checkBoxBusiness.setEnabled(false);
                                    viewHolder.checkBoxSUV.setEnabled(false);
                                    viewHolder.checkBoxPremium.setEnabled(false);
                                } else {
                                    ordersList.remove(ordersList.size() - 1);
                                    viewHolder.checkBoxSedan.setEnabled(true);
                                    viewHolder.checkBoxBusiness.setEnabled(true);
                                    viewHolder.checkBoxSUV.setEnabled(true);
                                    viewHolder.checkBoxPremium.setEnabled(true);
                                }
                            }
                        });
                        viewHolder.checkBoxSedan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_cat_sedan.getText().toString(),Integer.parseInt(viewHolder.txt_price_sedan.getText().toString()), idclient.getText().toString(), idorder.getText().toString());
                                    ordersList.add(fullOrders);
                                    viewHolder.checkBoxBusiness.setEnabled(false);
                                    viewHolder.checkBoxBigSUV.setEnabled(false);
                                    viewHolder.checkBoxSUV.setEnabled(false);
                                    viewHolder.checkBoxPremium.setEnabled(false);
                                } else {
                                    ordersList.remove(ordersList.size() - 1);
                                    viewHolder.checkBoxBusiness.setEnabled(true);
                                    viewHolder.checkBoxBigSUV.setEnabled(true);
                                    viewHolder.checkBoxSUV.setEnabled(true);
                                    viewHolder.checkBoxPremium.setEnabled(true);
                                }
                            }
                        });
//                            viewHolder.txt_child_text.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
////                                checkBox.setChecked(true);
//                                    Toast.makeText(ActivityFullOrder.this, "" + viewHolder.txt_child_text.getText(), Toast.LENGTH_SHORT).show();
//                                    fullOrders = new FullOrders(str, viewHolder.txt_child_text.getText().toString(), 23, idclient.getText().toString(), idorder.getText().toString());
//                                    ordersList.add(fullOrders);
////                                myDbReferenceOrder = database.getReference("FullOrders");
////                                key = myDbReferenceOrder.push().getKey();
//                                    // добавление заказа
//
////                                myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(ordersList);
//                                }
//
//                            });
//
//
//                        viewHolder.setiItemClickListener(new IItemClickListener() {
//                            @Override
//                            public void onClick(View view, int position) {
//                                Toast.makeText(ActivityFullOrder.this, "" + fullOrder.getText(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
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
                .child("OrderCWStandart");
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
