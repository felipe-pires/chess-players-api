package com.chess.players.model;

public class Event {

    private String name;
    private String start;
    private String end;
    private String place;

    public Event(String name, String place, String start, String end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.place = place;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", start='" + getStart() + "'" +
                ", end='" + getEnd() + "'" +
                ", place='" + getPlace() + "'" +
                "}";
    }
}
