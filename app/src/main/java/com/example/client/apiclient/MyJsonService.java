package com.example.client.apiclient;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface MyJsonService {
    @GET("/Event.json")
    void listEvents(Callback<List<Event>> eventsCallback);
}
