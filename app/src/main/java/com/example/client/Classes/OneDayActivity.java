package com.example.client.Classes;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.client.Fragments.OneDay;
import com.example.client.MainActivity;
import com.example.client.Models.Order;
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

public class OneDayActivity extends OneDay {
//    public int startTimeHour;
//    public int startTimeMinute;
//    public int startDayOfMonth;
//    public int startTimeMonth;
//    public int startTimeYear;
//    public int endTimeHour;
//    public int endTimeMinute;
//    public int endDayOfMonth;
//    public int endTimeMonth;
//    public int endTimeYear;

//    // подключение к БД
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    // получение ссылки на БД
//    DatabaseReference myDbReference = database.getReference();
//    // поключение к child Orders
//    DatabaseReference orderRef = myDbReference.child("Orders");
//    List<DataSnapshot> list;
//
//
//ValueEventListener listener = new ValueEventListener() {
//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        Order order = dataSnapshot.getValue(Order.class);
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//    }
//}

    //


//    @Override
//    public void onStart() {
//        super.onStart();
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list = new ArrayList<DataSnapshot>();
//                dataSnapshot.getChildren();
//                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    list.add(snapshot);
//                }
////                List<WeekViewEvent> viewEventList = mapDatatoCalendar(list);
////                for (DataSnapshot ds:dataSnapshot.getChildren()){
////                    startTimeHour = ds.child("startTimeHour").getValue(Integer.class);
////                    startTimeMinute = ds.child("startTimeMinute").getValue(Integer.class);
////                    startDayOfMonth = ds.child("startDayOfMonth").getValue(Integer.class);
////                    startTimeMonth = ds.child("startTimeMonth").getValue(Integer.class);
////                    startTimeYear = ds.child("startTimeYear").getValue(Integer.class);
////                    endTimeHour = ds.child("endTimeHour").getValue(Integer.class);
////                    endTimeMinute = ds.child("endTimeMinute").getValue(Integer.class);
////                    endDayOfMonth = ds.child("endDayOfMonth").getValue(Integer.class);
////                    endTimeMonth = ds.child("endTimeMonth").getValue(Integer.class);
////                    endTimeYear = ds.child("endTimeYear").getValue(Integer.class);
////                    String _startTimeHour = String.valueOf(startTimeHour);
////                    String _startTimeMinute = String.valueOf(startTimeMinute);
////                    String _startDayOfMonth = String.valueOf(startDayOfMonth);
////                    String _startTimeMonth = String.valueOf(startTimeMonth);
////                    String _startTimeYear = String.valueOf(startTimeYear);
////                    String _endTimeMinute = String.valueOf(endTimeHour);
////                    String _endTimeHour = String.valueOf(endTimeMinute);
////                    String _endDayOfMonth = String.valueOf(endDayOfMonth);
////                    String _endTimeMonth = String.valueOf(endTimeMonth);
////                    String _endTimeYear = String.valueOf(endTimeYear);
////                    Log.d("TAG", _startTimeHour + " " + _startTimeMinute + " " + _startDayOfMonth + " " + _startTimeMonth
////                            + " " + _startTimeYear + " " +  _endTimeHour + " " + _endTimeMinute + " " + _endDayOfMonth + " " + _endTimeMonth
////                            + " " + _endTimeYear);
////                    list.add(startTimeHour);
////                }
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        orderRef.addListenerForSingleValueEvent(listener);
//    }

    private List<WeekViewEvent> mapDatatoCalendar(List<DataSnapshot> snapshots){
        List<WeekViewEvent> result = new ArrayList<>();
        for (int i = 0; i < snapshots.size(); i++) {
//            Order order = new Order("",snapshots.get(i).child("startTimeHour").getValue(Integer.class),
//                    snapshots.get(i).child("startTimeMinute").getValue(Integer.class),
//                    snapshots.get(i).child("startDayOfMonth").getValue(Integer.class),
//                    snapshots.get(i).child("startTimeMonth").getValue(Integer.class),
//                    snapshots.get(i).child("startTimeYear").getValue(Integer.class),
//                    snapshots.get(i).child("endTimeHour").getValue(Integer.class),
//                    snapshots.get(i).child("endTimeMinute").getValue(Integer.class),
//                    snapshots.get(i).child("endDayOfMonth").getValue(Integer.class),
//                    snapshots.get(i).child("endTimeMonth").getValue(Integer.class),
//                    snapshots.get(i).child("endTimeYear").getValue(Integer.class));

            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.DAY_OF_MONTH, snapshots.get(i).child("startDayOfMonth").getValue(Integer.class));
            startTime.set(Calendar.HOUR_OF_DAY, snapshots.get(i).child("startTimeHour").getValue(Integer.class));
            startTime.set(Calendar.MINUTE, snapshots.get(i).child("startTimeMinute").getValue(Integer.class));
            startTime.set(Calendar.MONTH, snapshots.get(i).child("startTimeMonth").getValue(Integer.class)-1);
            startTime.set(Calendar.YEAR, snapshots.get(i).child("startTimeYear").getValue(Integer.class));
            Calendar endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.DAY_OF_MONTH, snapshots.get(i).child("endDayOfMonth").getValue(Integer.class));
            endTime.set(Calendar.HOUR_OF_DAY, snapshots.get(i).child("endTimeHour").getValue(Integer.class));
            WeekViewEvent event = new WeekViewEvent(snapshots.get(i).getKey().hashCode(), getEventTitle(startTime), startTime, endTime);
            event.setColor(getResources().getColor(R.color.event_color_03));

            result.add(event);
        }
 return result;
    }


        @Override
        public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

            // Populate the week view with some events.
            List<WeekViewEvent> events = mapDatatoCalendar(((MainActivity) getActivity()).list);


//        Calendar startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 1);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 4);
//        endTime.set(Calendar.MINUTE, 30);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 4);
//        startTime.set(Calendar.MINUTE, 20);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 5);
//        endTime.set(Calendar.MINUTE, 0);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 2);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth - 1);
//        startTime.set(Calendar.YEAR, newYear);
//        startTime.add(Calendar.DATE, 1);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 15);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 1);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
//        startTime.set(Calendar.HOUR_OF_DAY, 15);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        //AllDay event
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 0);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 23);
//        event = new WeekViewEvent(7, getEventTitle(startTime), startTime, endTime);
//
////        event = new WeekViewEvent(7, getEventTitle(startTime),null, startTime, endTime, true);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 8);
//        startTime.set(Calendar.HOUR_OF_DAY, 2);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.DAY_OF_MONTH, 10);
//        endTime.set(Calendar.HOUR_OF_DAY, 20);
//        event = new WeekViewEvent(8, getEventTitle(startTime), startTime, endTime);
////        event = new WeekViewEvent(8, getEventTitle(startTime),null, startTime, endTime, true);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        // All day event until 00:00 next day
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 10);
//        startTime.set(Calendar.HOUR_OF_DAY, 0);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.SECOND, 0);
//        startTime.set(Calendar.MILLISECOND, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.DAY_OF_MONTH, 11);
//        event = new WeekViewEvent(8, getEventTitle(startTime),startTime, endTime);
//
////        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime, true);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);

            return events;
        }
}
