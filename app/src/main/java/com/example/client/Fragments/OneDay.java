package com.example.client.Fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import com.example.client.Activity.RegularClientActivity;
import com.example.client.Classes.OneDayActivity;
import com.example.client.Models.Order;
import com.example.client.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public abstract class OneDay extends Fragment implements WeekView.EmptyViewClickListener, WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    private WeekView mWeekView;
    SpeedDialView speedDialView;
    private AlertDialog alertDialogOneDay;
    private AlertDialog.Builder alertDialogBuilder;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myDbReferenceOrder;
    private DatabaseReference myDbReferenceEventOrder;
    FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
    private Order order;
    private String key;
    TextView txtdateOrder;
    TextView txtTimeOrder;
    EditText addComment;
    int startOrderDay;
    int startOrderMonth;
    int startOrderYear;
    int startOrderHour =-1;
    int startOrderMinute=-1;
    private LayoutInflater layoutInflater;
    private View view;

    Timer mTimer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View showOneDay = inflater.inflate(R.layout.activity_week_view, container,false);
        mWeekView = showOneDay.findViewById(R.id.weekView);
        speedDialView = showOneDay.findViewById(R.id.speedDial);

        mTimer = new Timer();
        startAutoRefresh();
        mWeekView.setNumberOfVisibleDays(1);

        // Lets change some dimensions to best fit the view.
        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        mWeekView.setEmptyViewClickListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);
        // создание плавающей книпки для записи клиентов
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.carWashing_3Phases, R.drawable.car_wash_3)
                        .setLabel(R.string.CarWashing_3Phases)
                        .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.carWashing, R.drawable.car_wash_std)
                .setLabel(R.string.CarWashing)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.chemicalCleaningSalon, R.drawable.chemical_cleaning_salon)
                .setLabel(R.string.ChemicalCleaningSalon)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.nanoCeramics, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.NanoCeramics)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.polishing, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.Polishing)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.protectiveFilm, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.ProtectiveFilm)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.repairWindshield, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.RepairWindshield)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.salonProtection, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.SalonProtection)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.toning, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.Toning)
                .create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.tireFitting, R.drawable.ic_list_white_24dp)
                .setLabel(R.string.TireFitting)
                .create());

        // создание обработчиков клика по выбранному пункту плавающей копки
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()) {
                    case R.id.carWashing_3Phases:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);;
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+2, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#D50000", 1, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+2, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#D50000", 1, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.carWashing:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+1, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#AA00FF", 2, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+1, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#AA00FF", 2, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.repairWindshield:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try{
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+3, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#304FFE", 3, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+3, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#304FFE", 3, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });
                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.polishing:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+1, startOrderMonth, startOrderYear, "#0097A7", 4, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+1, startOrderMonth, startOrderYear, "#0097A7", 4, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.nanoCeramics:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+2, startOrderMonth, startOrderYear, "#00C853", 5, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+2, startOrderMonth, startOrderYear, "#00C853", 5, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });
                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.protectiveFilm:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+2, startOrderMonth, startOrderYear, "#AEEA00", 6, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+2, startOrderMonth, startOrderYear, "#AEEA00", 6, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.chemicalCleaningSalon:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+6, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#FFAB00", 7, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+6, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#FFAB00", 7, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.salonProtection:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+6, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#795548", 8, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+6, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#795548", 8, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.tireFitting:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+1, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#212121", 9, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour+1, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear, "#212121", 9, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });

                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;

                    case R.id.toning:
                        alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setView(R.layout.add_order);
                        alertDialogBuilder.setTitle(R.string.addOrder);
                        layoutInflater = getLayoutInflater();
                        view = inflater.inflate(R.layout.add_order, null, false);
                        alertDialogBuilder.setView(view);
                        txtdateOrder = view.findViewById(R.id.dateOrder);
                        txtTimeOrder = view.findViewById(R.id.timeOrder);
                        addComment = view.findViewById(R.id.addComment);

                        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    order = new Order(user.getUid(), startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+1, startOrderMonth, startOrderYear, "#455A64", 10, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    order = new Order("user unregister", startOrderHour, startOrderMinute, startOrderDay, startOrderMonth, startOrderYear,
                                            startOrderHour, startOrderMinute, startOrderDay+1, startOrderMonth, startOrderYear, "#455A64", 10, addComment.getText().toString());
                                    myDbReferenceOrder = database.getReference("Orders");
//                            EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
//                            myDbReferenceEventOrder = database.getReference("Event");
                                    //Uid заказа
                                    key = myDbReferenceOrder.push().getKey();
                                    // добавление заказа
                                    myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
//                            myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                                    Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialogOneDay.dismiss();
                            }
                        });
                        alertDialogOneDay = alertDialogBuilder.create();
                        alertDialogOneDay.show();
                        txtdateOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                datePicker();
                            }
                        });
                        txtTimeOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                timePicker();
                            }
                        });
                        break;
                }
                return false;
            }
        });
        return showOneDay;
    }

    // Создание и отображение Диалогового окна TimePickerDialog

    public void timePicker (){
        Calendar calendar = Calendar.getInstance();
        startOrderHour = calendar.get(Calendar.HOUR_OF_DAY);
        startOrderMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        OneDay.this.startOrderHour=hourOfDay;
                        OneDay.this.startOrderMinute=minute;
                        txtTimeOrder.setText(String.format ("%02d:%02d", hourOfDay, minute));
                        String hour = hourOfDay + ":" + minute;
                        Toast.makeText(getActivity(), hour, Toast.LENGTH_SHORT).show();
                    }
                }, startOrderHour, startOrderMinute, true);
        timePickerDialog.show();
    }

    // создание DatePickerDialog
    public void datePicker (){
        Calendar calendar = Calendar.getInstance();
        startOrderDay = calendar.get (Calendar.DAY_OF_MONTH);
        startOrderMonth= calendar.get (Calendar.MONTH)+1;
        startOrderYear = calendar.get (Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), AlertDialog.THEME_TRADITIONAL){
            @Override
            public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
                OneDay.this.startOrderYear=year;
                OneDay.this.startOrderMonth= ++month;
                OneDay.this.startOrderDay=dayOfMonth;
            }
        };
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String date= startOrderDay+"/"+ startOrderMonth+"/"+startOrderYear;
                Toast.makeText(getContext(), date, Toast.LENGTH_SHORT).show();
                txtdateOrder.setText(String.format("%02d-%02d-%d", startOrderDay, startOrderMonth, startOrderYear));
            }
        });
        datePickerDialog.show();
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEEE", Locale.forLanguageTag("ru"));
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d MMMM", Locale.forLanguageTag("ru"));

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + ", " + format.format(date.getTime()).toUpperCase();
            }

            @Override
            public String interpretTime(int hour) {
                return String.format("%02d:00", hour);
            }
        });
    }

    protected String getEventTitle(Calendar time) {
//        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
        return String.format("", "");
    }


    protected String getTimeOrder(Calendar time) {
        return String.format("%02d:%02d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE));
    }

    protected String getDateOrder(Calendar time){
        return String.format("%1$te %1$tB %1$tY",time);
    }

    protected int getStartHour(Calendar time){
        return time.get(Calendar.HOUR_OF_DAY);
    }
    protected int getStartMinute(Calendar time){
        int minute =  time.get(Calendar.MINUTE);
        if(minute > 0 && minute < 29) {
            minute = 0;
        } else {
            minute = 30;
        }
        return minute;
    }
    protected int getStartDay(Calendar time){
        return time.get(Calendar.DAY_OF_MONTH);
    }
    protected int getStartMonth(Calendar time){
        return time.get(Calendar.MONTH)+1;
    }
    protected int getStartYear(Calendar time){
        return time.get(Calendar.YEAR);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onEmptyViewClicked(final Calendar time) {
//
//        popUpMenu(time);
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(getActivity(), "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();

    }

    public WeekView getWeekView() {
        return mWeekView;
    }

    private void refreshFragment(){
        OneDay fragment = (OneDay)getFragmentManager().findFragmentById(R.id.fr);

        getFragmentManager().beginTransaction()
                .detach(fragment)
                .replace(R.id.fr, fragment, fragment.getClass().getCanonicalName())
                .attach(fragment)
                .addToBackStack(null)
                .commit();
    }

    private void startAutoRefresh() {

        mTimer.scheduleAtFixedRate(new TimerTask() {

                                       @Override
                                       public void run() {
                                           refreshFragment();
                                       }
                                   }
                , 60000      // Это задержка старта, сейчас 60 cek;
                , 600000); // Это период в 10 минут;
    }
    public void stopAutoRefresh(){
        mTimer.cancel();
    }
}
