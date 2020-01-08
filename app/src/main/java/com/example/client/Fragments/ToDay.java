package com.example.client.Fragments;

import android.graphics.RectF;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.client.Models.EventOrder;
import com.example.client.Models.Order;
import com.example.client.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public abstract class ToDay extends Fragment implements WeekView.EmptyViewClickListener, WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    private WeekView mWeekView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myDbReferenceOrder;
    private DatabaseReference myDbReferenceEventOrder;
    FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View showToDay = inflater.inflate(R.layout.activity_week_view, container,false);
        mWeekView = showToDay.findViewById(R.id.weekView);
        mWeekView.goToToday();

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

        return showToDay;
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d MMMM", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + ", " + format.format(date.getTime()).toUpperCase();
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
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
        return time.get(Calendar.MINUTE);
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
        Toast.makeText(getActivity(), "Empty view click pressed: " + getTimeOrder(time), Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
        PopupMenu popupMenu = new PopupMenu(getActivity(), mWeekView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()){
                    case R.id.addOrder:
                        Order order = new Order (user.getUid(), getStartHour(time), getStartMinute(time), getStartDay(time), getStartMonth(time),
                                getStartYear(time), getStartMinute(time), getStartHour(time), getStartMinute(time), getStartHour(time), getStartMinute(time));
                        myDbReferenceOrder = database.getReference("Orders");
                        EventOrder eventOrder = new EventOrder(user.getUid(), 5, "10:00", "12:00", "#59DBE0");
                        myDbReferenceEventOrder = database.getReference("Event");
                        //Uid заказа
                        String key = myDbReferenceOrder.push().getKey();
                        // добавление заказа
                        myDbReferenceOrder.child(Objects.requireNonNull(key)).setValue(order);
                        myDbReferenceEventOrder.child(Objects.requireNonNull(key)).setValue(eventOrder);
                        Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(getActivity(), "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();

    }

    public WeekView getWeekView() {
        return mWeekView;
    }
}
