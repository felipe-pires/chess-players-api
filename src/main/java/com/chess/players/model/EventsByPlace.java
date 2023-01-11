package com.chess.players.model;

import java.util.List;

public class EventsByPlace {
    private String place;
    private List<Event> events;

    public EventsByPlace(String place, List<Event> events) {
        this.place = place;
        this.events = events;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "{" +
                " place='" + getPlace() + "'" +
                ", events='" + getEvents() + "'" +
                "}";
    }
}
