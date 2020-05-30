package com.example.client.Fragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.example.client.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ToDay extends Fragment implements MonthLoader.MonthChangeListener {

    private WeekView mWeekView;
    Timer mTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View showToDay = inflater.inflate(R.layout.activity_start_view, container,false);
        mWeekView = showToDay.findViewById(R.id.weekView);

        mTimer = new Timer();
        startAutoRefresh();

        mWeekView.goToToday();

        // Lets change some dimensions to best fit the view.
        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        mWeekView.setMonthChangeListener(this);
        setupDateTimeInterpreter(false);

        Calendar calendar=Calendar.getInstance();
//        String month = new SimpleDateFormat("MMMM", new Locale("ru")).format(calendar.getTime());
        String[] monthNames = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };
        String month = monthNames[calendar.get(Calendar.MONTH)];
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(month);

        return showToDay;
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("E", Locale.forLanguageTag("ru"));
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d MMM", Locale.forLanguageTag("ru"));

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
        return String.format("", "");
    }
    public WeekView getWeekView() {
        return mWeekView;
    }

    private void startAutoRefresh() {

        mTimer.scheduleAtFixedRate(new TimerTask() {

                                       @Override
                                       public void run() {
                                           ToDay fragment = (ToDay) getFragmentManager().findFragmentById(R.id.fr);
                                           getFragmentManager().beginTransaction()
                                                   .detach(fragment)
                                                   .attach(fragment)
                                                   .commit();
                                       }
                                   }
                , 60000      // Это задержка старта, сейчас 60 cek;
                , 600000); // Это период в 10 минут;
    }

    public void stopAutoRefresh(){
        mTimer.cancel();
    }
}
