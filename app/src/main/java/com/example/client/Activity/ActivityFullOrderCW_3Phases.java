package com.example.client.Activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class ActivityFullOrderCW_3Phases extends AppCompatActivity {
    float x1, x2, y1,y2;
    RecyclerView recyclerView;
    List<FullOrder> fullOrderList = new ArrayList<>();
    FirebaseRecyclerAdapter<FullOrder, FullOrderViewHolder> adapter;
    public Button buttonOrder;
    public TextView idorder;
    public TextView idclient;
    //    MaskedEditText phoneClient;
    MaskedEditText phoneClient;
    public EditText govNumCar;
    public Button searchPhoneClient;
    public Button searchGovNumber;
    public CheckBox checkUnregClient;
    List<FullOrders> ordersList = new ArrayList<>();
    FullOrders fullOrders;
    private DatabaseReference myDbReferenceOrder;
    private DatabaseReference spinnerDbReferenceOrder;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseDatabase spinnerDb = FirebaseDatabase.getInstance();
    private String key;
    private String spinnerKey;
    public FullOrderViewHolder viewHolder;
    SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
    Date currentDate;
    DateFormat dateFormat;
    DateFormat timeFormat;
    public String dateText;
    public String timeText;

    public String titleService;
    Toolbar myToolbar;
    Spinner mySpinner;
    Spinner selectOrder;

    Spinner boxSpinner;

    private String textData;
    private String boxData;

    ArrayAdapter<String> adapterSpinner;
    ArrayList<String> spinnerListOrder;

    public Boolean spinnerTouched = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_full_order);

        String txtQrCode = getIntent().getStringExtra("qrCode");

        spinnerDbReferenceOrder = spinnerDb.getReference("Orders");
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        boxSpinner = (Spinner) findViewById(R.id.idBox);
        selectOrder = (Spinner) findViewById(R.id.idOrder);
        myToolbar.setTitle("3-х фазная мойка");
        getSupportActionBar().hide();

        spinnerListOrder = new ArrayList<>();
        adapterSpinner = new ArrayAdapter<String>(ActivityFullOrderCW_3Phases.this, android.R.layout.simple_spinner_dropdown_item,spinnerListOrder);
       selectOrder.setAdapter(adapterSpinner);


        buttonOrder = findViewById(R.id.buttonOrders);
        idclient = findViewById(R.id.id_Client);
        idclient.setText(txtQrCode);
        idorder = findViewById(R.id.id_Order);
        checkUnregClient = findViewById(R.id.userChekUnreg);
        phoneClient = findViewById(R.id.phoneUnregClient);
        govNumCar = findViewById(R.id.govNumberCarClient);
        setUpperCase();
        searchPhoneClient = findViewById(R.id.searchPhone);
        searchGovNumber = findViewById(R.id.searchGovNumber);
        phoneClient.setVisibility((View.GONE));
        govNumCar.setVisibility((View.GONE));
        searchPhoneClient.setVisibility((View.GONE));
        searchGovNumber.setVisibility((View.GONE));
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

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ActivityFullOrderCW_3Phases.this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> boxAdapter = new ArrayAdapter<String>(ActivityFullOrderCW_3Phases.this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.boxes));

        boxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boxSpinner.setAdapter(boxAdapter);

        searchGovNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // запрос на выборку заказов по номеру автомобиля клиента
                Query query = spinnerDbReferenceOrder
                        .orderByChild("carGovNumber")
                        .equalTo(govNumCar.getText().toString());
                query.addListenerForSingleValueEvent(listener);
            }
        });

        searchPhoneClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // запрос на выборку заказов по номеру телефона клиента
                Query query = spinnerDbReferenceOrder
                        .orderByChild("phoneNumberClient")
                        .equalTo(phoneClient.getText().toString());
                query.addListenerForSingleValueEvent(listener);
            }
        });

        mySpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spinnerTouched = true;
                return false;
            }
        });

        boxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.DKGRAY);
                boxData = boxSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        selectOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textData = selectOrder.getSelectedItem().toString();
                textData = textData.substring(0, textData.lastIndexOf('(')).trim();
                idorder.setText(textData);
                Toast.makeText(ActivityFullOrderCW_3Phases.this, selectOrder.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(spinnerTouched){
                    Toast.makeText(ActivityFullOrderCW_3Phases.this,  mySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    switch (position) {
                        case 0:
                            break;
                        case 1:
                            Intent cwStandart = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrderCW_Standart.class);
                            startActivity(cwStandart);
                            finish();
                            break;
                        case 2:
                            Intent repairWindshield = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_RepairWindshield.class);
                            startActivity(repairWindshield);
                            finish();
                            break;
                        case 3:
                            Intent polishing = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_Polishing.class);
                            startActivity(polishing);
                            finish();
                            break;
                        case 4:
                            Intent nanoCeramics = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_Nanoceramics.class);
                            startActivity(nanoCeramics);
                            break;
                        case 5:
                            Intent protectiveFilm = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_ProtectiveFilm.class);
                            startActivity(protectiveFilm);
                            finish();
                            break;
                        case 6:
                            Intent chemicalCleaning = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_ChemicalCleaningSalon.class);
                            startActivity(chemicalCleaning);
                            finish();
                            break;
                        case 7:
                            Intent protectiveSalon = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_SalonProtection.class);
                            startActivity(protectiveSalon);
                            finish();
                            break;
                        case 8:
                            Intent tireFitting = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_TireFitting.class);
                            startActivity(tireFitting);
                            finish();
                            break;
                        case 9:
                            Intent toning = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrder_Toning.class);
                            startActivity(toning);
                            finish();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // запрос на выборку заказов по ИД клиента 
        Query query = spinnerDbReferenceOrder
                .orderByChild("UserId")
                .equalTo(idclient.getText().toString());
        query.addListenerForSingleValueEvent(listener);

        idclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFullOrderCW_3Phases.this, ActivityQrCodeReader.class);
                intent.putExtra("titleService", "CW_3Phases");
                startActivity(intent);
            }
        });
        checkUnregClient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    idclient.setVisibility(View.VISIBLE);
                    phoneClient.setVisibility((View.GONE));
                    govNumCar.setVisibility((View.GONE));
                    searchPhoneClient.setVisibility((View.GONE));
                    searchGovNumber.setVisibility((View.GONE));
                }
                else {
                    idclient.setVisibility(View.GONE);
                    phoneClient.setVisibility((View.VISIBLE));
                    govNumCar.setVisibility((View.VISIBLE));
                    searchPhoneClient.setVisibility((View.VISIBLE));
                    searchGovNumber.setVisibility((View.VISIBLE));
                    Toast.makeText(ActivityFullOrderCW_3Phases.this, "bad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData() {
        Query query = FirebaseDatabase.getInstance().getReference().child("OrderCW3Phases");
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
                                Toast.makeText(ActivityFullOrderCW_3Phases.this, "without child"+fullOrderList.get(position).getTitleService(), Toast.LENGTH_SHORT).show();
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
                                    getCurrentDateTime();
                                    if (idclient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_premium.getText().toString(), Integer.parseInt(viewHolder.txt_price_premium.getText().toString()), idclient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if (phoneClient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_premium.getText().toString(), Integer.parseInt(viewHolder.txt_price_premium.getText().toString()), phoneClient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if(govNumCar.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_premium.getText().toString(), Integer.parseInt(viewHolder.txt_price_premium.getText().toString()), govNumCar.getText().toString(), idorder.getText().toString(), dateText, timeText,boxData);
                                    }
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
                                    getCurrentDateTime();
                                    if (idclient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_business.getText().toString(), Integer.parseInt(viewHolder.txt_price_business.getText().toString()), idclient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if (phoneClient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_business.getText().toString(), Integer.parseInt(viewHolder.txt_price_business.getText().toString()), phoneClient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if(govNumCar.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_business.getText().toString(), Integer.parseInt(viewHolder.txt_price_business.getText().toString()), govNumCar.getText().toString(), idorder.getText().toString(), dateText, timeText,boxData);
                                    }
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
                                    getCurrentDateTime();
                                    if (idclient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_SUV.getText().toString(), Integer.parseInt(viewHolder.txt_price_SUV.getText().toString()), idclient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if (phoneClient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_SUV.getText().toString(), Integer.parseInt(viewHolder.txt_price_SUV.getText().toString()), phoneClient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if(govNumCar.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_SUV.getText().toString(), Integer.parseInt(viewHolder.txt_price_SUV.getText().toString()), govNumCar.getText().toString(), idorder.getText().toString(), dateText, timeText,boxData);
                                    }
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
                                    getCurrentDateTime();
                                    if (idclient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_BigSUV.getText().toString(), Integer.parseInt(viewHolder.txt_price_BigSUV.getText().toString()), idclient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if (phoneClient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_BigSUV.getText().toString(), Integer.parseInt(viewHolder.txt_price_BigSUV.getText().toString()), phoneClient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if(govNumCar.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_BigSUV.getText().toString(), Integer.parseInt(viewHolder.txt_price_BigSUV.getText().toString()), govNumCar.getText().toString(), idorder.getText().toString(), dateText, timeText,boxData);
                                    }
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
                                    getCurrentDateTime();
                                    if (idclient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_sedan.getText().toString(), Integer.parseInt(viewHolder.txt_price_sedan.getText().toString()), idclient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if (phoneClient.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_sedan.getText().toString(), Integer.parseInt(viewHolder.txt_price_sedan.getText().toString()), phoneClient.getText().toString(), idorder.getText().toString(), dateText, timeText, boxData);
                                    } else if(govNumCar.length()>0){
                                        fullOrders = new FullOrders(viewHolder.txt_idService.getText().toString(), viewHolder.txt_titleService.getText().toString(), viewHolder.txt_cat_sedan.getText().toString(), Integer.parseInt(viewHolder.txt_price_sedan.getText().toString()), govNumCar.getText().toString(), idorder.getText().toString(), dateText, timeText,boxData);
                                    }
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
                .child("OrderCW3Phases");
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

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot ds:dataSnapshot.getChildren()){
                spinnerKey = ds.getKey();
                spinnerListOrder.add(spinnerKey.toString()+" (дата ордера: "+ds.child("startDayOfMonth").getValue().toString()
                        +"."+ds.child("startTimeMonth").getValue().toString()+"."+ds.child("startTimeYear").getValue().toString()+")");
            }
            adapterSpinner.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


//    public void retrieveDataSpinnerOrder(){
//
//        listener = spinnerDbReferenceOrder.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    spinnerKey = ds.getKey();
//                    spinnerListOrder.add(spinnerKey.toString()+" (дата ордера: "+ds.child("startDayOfMonth").getValue().toString()
//                    +"."+ds.child("startTimeMonth").getValue().toString()+"."+ds.child("startTimeYear").getValue().toString()+")");
//                }
//                adapterSpinner.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=touchEvent.getX();
                y1=touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=touchEvent.getX();
                y2=touchEvent.getY();
                if (x1<x2){
                    Intent intent = new Intent(ActivityFullOrderCW_3Phases.this, ActivityFullOrderCW_Standart.class);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }

    public void getCurrentDateTime(){
        // Текущее время
        currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
// Форматирование времени как "часы:минуты:секунды"
        timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        dateText = dateFormat.format(currentDate);
        timeText = timeFormat.format(currentDate);
    }

    public void setUpperCase(){
        govNumCar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strGovNumber = s.toString();
                if(!strGovNumber.equals(strGovNumber.toUpperCase())){
                    strGovNumber = strGovNumber.toUpperCase();
                    govNumCar.setText(strGovNumber);
                }
                govNumCar.setSelection(govNumCar.getText().length());
            }
        });
    }
}
