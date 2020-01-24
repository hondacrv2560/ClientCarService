package com.example.client.Classes;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alamkanak.weekview.WeekViewEvent;
import com.example.client.Fragments.OneDay;
import com.example.client.Fragments.ThreeDays;
import com.example.client.Fragments.ToDay;
import com.example.client.MainActivity;
import com.example.client.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ThreeDaysActivity extends ThreeDays {
    private List<WeekViewEvent> mapDatatoCalendar(List<DataSnapshot> snapshots) {
        List<WeekViewEvent> result = new ArrayList<>();
        for (int i = 0; i < snapshots.size(); i++) {
            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(snapshots.get(i).child("startDayOfMonth").getValue().toString()));
            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(snapshots.get(i).child("startTimeHour").getValue().toString()));
            startTime.set(Calendar.MINUTE, Integer.parseInt(snapshots.get(i).child("startTimeMinute").getValue().toString()));
            startTime.set(Calendar.MONTH, Integer.parseInt(snapshots.get(i).child("startTimeMonth").getValue().toString()) - 1);
            startTime.set(Calendar.YEAR, Integer.parseInt(snapshots.get(i).child("startTimeYear").getValue().toString()));
            Calendar endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(snapshots.get(i).child("endDayOfMonth").getValue().toString()));
            endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(snapshots.get(i).child("endTimeHour").getValue().toString()));
            WeekViewEvent event = new WeekViewEvent(snapshots.get(i).getKey().hashCode(), getEventTitle(startTime), startTime, endTime);
            event.setColor(getResources().getColor(R.color.event_color_03));


            result.add(event);
        }
        return result;
    }

    List<WeekViewEvent> events;

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        if (events != null) {
            if (events.size() > 0) {
                events.clear();
            }
        }
        events = mapDatatoCalendar(((MainActivity) getActivity()).list);
        return events;
    }
}
